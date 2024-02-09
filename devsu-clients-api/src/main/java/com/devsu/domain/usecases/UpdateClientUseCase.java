package com.devsu.domain.usecases;

import com.devsu.adapters.in.ClientResponse;
import com.devsu.adapters.in.UpdateClientRequest;
import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.ports.in.UpdateClientPort;
import com.devsu.domain.ports.out.ClientPersistence;
import com.devsu.domain.ports.out.ClientPersistencePort;
import java.util.Optional;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateClientUseCase implements UpdateClientPort {

  private final ClientPersistencePort clientPersistencePort;
  @Override
  public ClientResponse execute(UpdateClientRequest request) {
    checkClientExistence(request);
    return buildClientPersistenceFromRequest(request)
            .map(client -> clientPersistencePort.saveClient(client))
            .map(buildClientResponse())
            .get();
  }

  private void checkClientExistence(UpdateClientRequest request){
    Optional.ofNullable(request.getClientId())
      .map(clientId -> clientPersistencePort.getClientById(clientId))
      .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorsEnum.CLIENT_NOT_FOUND));
  }
  private Function<ClientPersistence, ClientResponse> buildClientResponse() {
    return clientPersistence -> ClientResponse.builder()
            .identificacion(clientPersistence.getIdentificacion())
            .nombre(clientPersistence.getNombre())
            .telefono(clientPersistence.getTelefono())
            .direccion(clientPersistence.getDireccion())
            .edad(clientPersistence.getEdad())
            .estado(clientPersistence.getEstado())
            .genero(clientPersistence.getGenero())
            .clientId(clientPersistence.getId())
            .build();
  }

  private Optional<ClientPersistence> buildClientPersistenceFromRequest(UpdateClientRequest request){
    return Optional.of(ClientPersistence.builder()
            .telefono(request.getTelefono())
            .id(request.getClientId())
            .password(request.getPassword())
            .edad(request.getEdad())
            .nombre(request.getNombre())
            .identificacion(request.getIdentificacion())
            .genero(request.getGenero())
            .direccion(request.getDireccion())
            .estado(request.getEstado())
            .build());
  }
}
