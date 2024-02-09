package com.devsu.domain.usecases;

import com.devsu.adapters.in.NewClientRequest;
import com.devsu.adapters.in.ClientResponse;
import com.devsu.domain.ports.in.CreateClientPort;
import com.devsu.domain.ports.out.ClientPersistence;
import com.devsu.domain.ports.out.ClientPersistencePort;
import java.util.Optional;
import java.util.function.Function;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateClientUseCase implements CreateClientPort {

  private final ClientPersistencePort clientPersistencePort;
  @Override
  public ClientResponse execute(NewClientRequest request) {
    return buildClientPersistenceFromRequest(request)
            .map(client -> clientPersistencePort.saveClient(client))
            .map(buildClientResponse())
            .get();
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
            .build();
  }

  private Optional<ClientPersistence> buildClientPersistenceFromRequest(NewClientRequest request){
    return Optional.of(ClientPersistence.builder()
            .telefono(request.getTelefono())
            .password(request.getPassword())
            .edad(request.getEdad())
            .nombre(request.getNombre())
            .identificacion(request.getIdentificacion())
            .genero(request.getGenero())
            .estado(true)
            .direccion(request.getDireccion())
            .build());
  }
}
