package com.devsu.domain.ports.out;

import java.util.List;

public interface ClientPersistencePort {
  ClientPersistence saveClient(ClientPersistence client);

  ClientPersistence getClientById(Integer clientId);
  List<ClientPersistence> getClients();

  Void deleteClient(Integer clientId);
}
