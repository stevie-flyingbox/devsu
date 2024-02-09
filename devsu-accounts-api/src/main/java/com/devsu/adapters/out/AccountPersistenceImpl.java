package com.devsu.adapters.out;

import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.ports.out.AccountPersistence;
import com.devsu.domain.ports.out.AccountPersistencePort;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Slf4j
public class AccountPersistenceImpl implements AccountPersistencePort {
  private final AccountRepository accountRepository;

  @Override
  @Transactional
  public AccountPersistence saveAccount(AccountPersistence accountPersistence) {
    try{
      AccountEntity accountEntity = accountRepository.save(buildAccountEntity(accountPersistence));
      return buildClientPersistence(accountEntity);
    }
    catch (Exception e){
      log.error("Error al guardar informacion de cuenta", e);
      throw new ApiException(HttpStatus.CONFLICT, ErrorsEnum.SAVE_ERROR);
    }
  }

  private AccountEntity buildAccountEntity(AccountPersistence accountPersistence) {
    return AccountEntity.builder()
            .idCuenta(accountPersistence.getId())
            .idCliente(accountPersistence.getClientId())
            .tipo(accountPersistence.getTipo())
            .estado(accountPersistence.getEstado())
            .numero(accountPersistence.getNumero())
            .saldoInicial(accountPersistence.getSaldoInicial())
            .build();
  }

  @Override
  public AccountPersistence getAccountById(Integer clientId, Integer accountId) {
    try{
      AccountEntity accountEntity = accountRepository.findByIdClienteAndIdCuenta(clientId, accountId)
              .orElseThrow();

      return buildClientPersistence(accountEntity);
    }
    catch (Exception e){
      log.error("Error al obtener cuentas", e);
      throw new ApiException(HttpStatus.NOT_FOUND, ErrorsEnum.INVALID_ACCOUNT);
    }
  }

  @Override
  public List<AccountPersistence> getAccountsByClientId(Integer clientId) {
    try{
      return accountRepository.findByIdCliente(clientId).stream()
              .map(this::buildClientPersistence)
              .collect(Collectors.toList());
    }
    catch (Exception e){
      log.error("Error al obtener cuenta", e);
      throw new ApiException(HttpStatus.NOT_FOUND, ErrorsEnum.CLIENT_NOT_FOUND);
    }
  }

  private AccountPersistence buildClientPersistence(AccountEntity entity){
    return AccountPersistence.builder()
            .id(entity.getIdCuenta())
            .clientId(entity.getIdCliente())
            .tipo(entity.getTipo())
            .numero(entity.getNumero())
            .saldoInicial(entity.getSaldoInicial())
            .estado(entity.getEstado())
            .build();
  }
  @Override
  public Void deleteAccount(Integer clientId, Integer accountId) {
    try{
      accountRepository.deleteById(clientId);
    }
    catch (Exception e){
      log.error("Error al intentar eliminar cuenta", e);
      throw new ApiException(HttpStatus.CONFLICT, ErrorsEnum.DELETE_ERROR);
    }
    return null;
  }

}
