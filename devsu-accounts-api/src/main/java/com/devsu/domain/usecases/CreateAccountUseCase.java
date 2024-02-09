package com.devsu.domain.usecases;

import com.devsu.adapters.in.AccountResponse;
import com.devsu.adapters.in.NewAccountRequest;
import com.devsu.domain.models.Client;
import com.devsu.domain.ports.in.CreateAccountPort;
import com.devsu.domain.ports.out.AccountPersistence;
import com.devsu.domain.ports.out.AccountPersistencePort;
import com.devsu.domain.ports.out.ClientPort;
import java.util.Optional;
import java.util.function.Function;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAccountUseCase implements CreateAccountPort {

  private final AccountPersistencePort accountPersistencePort;
  private final ClientPort clientPort;
  @Override
  public AccountResponse execute(NewAccountRequest request) {
    return Optional.of(clientPort.getClient(request.getClientId()))
            .map(buildAccountPersistence(request))
            .map(account -> accountPersistencePort.saveAccount(account))
            .map(buildClientResponse())
            .get();
  }

  private Function<Client, AccountPersistence> buildAccountPersistence(NewAccountRequest request){
    return client -> AccountPersistence.builder()
            .clientId(client.getId())
            .saldoInicial(request.getSaldoInicial())
            .numero(request.getNumero())
            .tipo(request.getTipo())
            .estado(true)
            .build();
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


}
