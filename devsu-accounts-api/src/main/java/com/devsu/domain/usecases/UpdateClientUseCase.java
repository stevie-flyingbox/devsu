package com.devsu.domain.usecases;

import com.devsu.adapters.in.AccountResponse;
import com.devsu.adapters.in.UpdateAccountRequest;
import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.ports.in.UpdateAccountPort;
import com.devsu.domain.ports.out.AccountPersistence;
import com.devsu.domain.ports.out.AccountPersistencePort;
import java.util.Optional;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateClientUseCase implements UpdateAccountPort {

  private final AccountPersistencePort accountPersistencePort;
  @Override
  public AccountResponse execute(UpdateAccountRequest request) {
    checkClientExistence(request);
    return buildClientPersistenceFromRequest(request)
            .map(client -> accountPersistencePort.saveAccount(client))
            .map(buildClientResponse())
            .get();
  }

  private void checkClientExistence(UpdateAccountRequest request){
    Optional.ofNullable(request.getClientId())
      .map(clientId -> accountPersistencePort.getAccountById(clientId, clientId))
      .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorsEnum.CLIENT_NOT_FOUND));
  }
  private Function<AccountPersistence, AccountResponse> buildClientResponse() {
    return accountPersistence -> AccountResponse.builder()
            .id(accountPersistence.getId())
            .tipo(accountPersistence.getTipo())
            .numero(accountPersistence.getNumero())
            .saldoInicial(accountPersistence.getSaldoInicial())
            .estado(accountPersistence.getEstado())
            .build();
  }

  private Optional<AccountPersistence> buildClientPersistenceFromRequest(UpdateAccountRequest request){
    return Optional.of(AccountPersistence.builder()
            .build());
  }
}
