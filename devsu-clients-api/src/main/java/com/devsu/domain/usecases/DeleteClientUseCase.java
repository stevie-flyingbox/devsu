package com.devsu.domain.usecases;

import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.ports.in.DeleteClientPort;
import com.devsu.domain.ports.out.ClientPersistence;
import com.devsu.domain.ports.out.ClientPersistencePort;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class DeleteClientUseCase implements DeleteClientPort {

  private final ClientPersistencePort clientPersistencePort;
  @Override
  public Void execute(Integer clientId) {
    return Optional.of(getClient(clientId))
            .map(client -> clientPersistencePort.deleteClient(clientId))
            .orElse(null);
  }

  private ClientPersistence getClient(Integer clientId){
    return Optional.ofNullable(clientId)
      .map(id -> clientPersistencePort.getClientById(id))
      .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorsEnum.CLIENT_NOT_FOUND));
  }

}
