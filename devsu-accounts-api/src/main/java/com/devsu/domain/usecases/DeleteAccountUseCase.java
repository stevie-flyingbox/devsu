package com.devsu.domain.usecases;

import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.ports.in.DeleteAccountPort;
import com.devsu.domain.ports.out.AccountPersistence;
import com.devsu.domain.ports.out.AccountPersistencePort;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class DeleteAccountUseCase implements DeleteAccountPort {

  private final AccountPersistencePort accountPersistencePort;
  @Override
  public Void execute(Integer clientId) {
    return Optional.of(getClient(clientId))
            .map(client -> accountPersistencePort.deleteAccount(clientId, null))
            .orElse(null);
  }

  private AccountPersistence getClient(Integer clientId){
    return Optional.ofNullable(clientId)
      .map(id -> accountPersistencePort.getAccountById(clientId, id))
      .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorsEnum.CLIENT_NOT_FOUND));
  }

}
