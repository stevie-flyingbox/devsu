package com.devsu.adapters.out;

import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.ports.out.ClientPersistence;
import com.devsu.domain.ports.out.ClientPersistencePort;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Slf4j
public class ClientPersistenceImpl implements ClientPersistencePort {
  private final ClientRepository clientRepository;

  @Override
  @Transactional
  public ClientPersistence saveClient(ClientPersistence clientPersistence) {
    try{
      ClientEntity clientEntity= clientRepository.save(buildClientEntity(clientPersistence));
      return buildClientPersistence(clientEntity);
    }
    catch (Exception e){
      log.error("Error al guardar informacion del cliente", e);
      throw new ApiException(HttpStatus.CONFLICT, ErrorsEnum.SAVE_ERROR);
    }
  }

  @Override
  public ClientPersistence getClientById(Integer clientId) {
    try{
      ClientEntity clientEntity= clientRepository.findById(clientId).get();

      return buildClientPersistence(clientEntity);
    }
    catch (NoSuchElementException e){
      log.error("Error al obtener clientes", e);
      throw new ApiException(HttpStatus.NOT_FOUND, ErrorsEnum.CLIENT_NOT_FOUND);
    }
    catch (Exception e){
      log.error("Error al obtener clientes", e);
      throw new ApiException(HttpStatus.NOT_FOUND, ErrorsEnum.CLIENT_NOT_FOUND);
    }
  }

  @Override
  public List<ClientPersistence> getClients() {
    try{
      List<ClientEntity> clients= new ArrayList<>();
      clientRepository.findAll().forEach(clients::add);
      return clients.stream().map(this::buildClientPersistence).collect(Collectors.toList());
    }
    catch (Exception e){
      log.error("Error al obtener clientes", e);
      throw new ApiException(HttpStatus.NOT_FOUND, ErrorsEnum.CLIENT_NOT_FOUND);
    }
  }

  @Override
  public Void deleteClient(Integer clientId) {
    try{
      clientRepository.deleteById(clientId);
    }
    catch (Exception e){
      log.error("Error al intentar eliminar cliente", e);
      throw new ApiException(HttpStatus.CONFLICT, ErrorsEnum.DELETE_ERROR);
    }
    return null;
  }

  private PersonEntity buildPersonEntity(ClientPersistence clientPersistence){
    return PersonEntity.builder()
            .direccion(clientPersistence.getDireccion())
            .edad(clientPersistence.getEdad())
            .genero(clientPersistence.getGenero())
            .nombre(clientPersistence.getNombre())
            .direccion(clientPersistence.getDireccion())
            .telefono(clientPersistence.getTelefono())
            .identificacion(clientPersistence.getIdentificacion())
            .build();
  }

  private ClientEntity buildClientEntity(ClientPersistence clientPersistence){
    return ClientEntity.builder()
            .estado(clientPersistence.getEstado())
            .persona(buildPersonEntity(clientPersistence))
            .id(clientPersistence.getId())
            .password(clientPersistence.getPassword())
            .build();
  }

  private ClientPersistence buildClientPersistence(PersonEntity person, ClientEntity client){
    return ClientPersistence.builder()
            .id(client.getId())
            .idPersona(person.getId())
            .direccion(person.getDireccion())
            .edad(person.getEdad())
            .estado(client.getEstado())
            .genero(person.getGenero())
            .identificacion(person.getIdentificacion())
            .nombre(person.getNombre())
            .telefono(person.getTelefono())
            .build();
  }

  private ClientPersistence buildClientPersistence(ClientEntity client){
    return ClientPersistence.builder()
            .id(client.getId())
            .idPersona(client.getPersona().getId())
            .direccion(client.getPersona().getDireccion())
            .edad(client.getPersona().getEdad())
            .estado(client.getEstado())
            .genero(client.getPersona().getGenero())
            .identificacion(client.getPersona().getIdentificacion())
            .nombre(client.getPersona().getNombre())
            .telefono(client.getPersona().getTelefono())
            .build();
  }
}
