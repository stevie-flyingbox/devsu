package com.devsu.domain.usecases;

import com.devsu.adapters.in.AccountResponse;
import com.devsu.domain.commands.GetAccountCommand;
import com.devsu.domain.ports.in.GetAccountPort;
import com.devsu.domain.ports.out.AccountPersistence;
import com.devsu.domain.ports.out.AccountPersistencePort;
import jakarta.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetAccountUseCase implements GetAccountPort {

  private final AccountPersistencePort accountPersistencePort;
  @Override
  public List<AccountResponse> execute(GetAccountCommand inputCommand) {
    return Optional.ofNullable(inputCommand.getAccountId())
            .map(getAccountById(inputCommand.getClientId()))
            .orElseGet(() -> getAllAccounts(inputCommand.getClientId()));
  }

  private Function<Integer, List<AccountResponse>> getAccountById(Integer clientId) {
    return accountId -> Optional.ofNullable(accountPersistencePort.getAccountById(clientId, accountId))
            .map(mapClientResponse())
            .map(List::of)
            .get();
  }

  private List<AccountResponse> getAllAccounts(Integer clientId) {
    return Optional.ofNullable(accountPersistencePort.getAccountsByClientId(clientId))
            .map(mapListClientResponse())
            .get();
  }
  private Function<AccountPersistence, AccountResponse> mapClientResponse() {
    return clientPersistence -> AccountResponse.builder()
            .id(clientPersistence.getId())
            .tipo(clientPersistence.getTipo())
            .numero(clientPersistence.getNumero())
            .saldoInicial(clientPersistence.getSaldoInicial())
            .estado(clientPersistence.getEstado())
            .build();
  }

  private Function<List<AccountPersistence>, List<AccountResponse>> mapListClientResponse() {
    return clientPersistenceList -> clientPersistenceList.stream()
            .map(mapClientResponse())
            .collect(Collectors.toList());
  }
}
