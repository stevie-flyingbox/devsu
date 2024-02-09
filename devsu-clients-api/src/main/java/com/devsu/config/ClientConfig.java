package com.devsu.config;

import com.devsu.domain.ports.in.CreateClientPort;
import com.devsu.domain.ports.in.DeleteClientPort;
import com.devsu.domain.ports.in.GetClientsPort;
import com.devsu.domain.ports.in.UpdateClientPort;
import com.devsu.domain.ports.out.ClientPersistencePort;
import com.devsu.domain.usecases.CreateClientUseCase;
import com.devsu.domain.usecases.DeleteClientUseCase;
import com.devsu.domain.usecases.GetClientsUseCase;
import com.devsu.domain.usecases.UpdateClientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
  @Bean
  public CreateClientPort createClientUseCase(ClientPersistencePort clientPersistencePort) {
    return new CreateClientUseCase(clientPersistencePort);
  }

  @Bean
  public GetClientsPort getClientsUseCase(ClientPersistencePort clientPersistencePort) {
    return new GetClientsUseCase(clientPersistencePort);
  }

  @Bean
  public UpdateClientPort updateClientUseCase(ClientPersistencePort clientPersistencePort) {
    return new UpdateClientUseCase(clientPersistencePort);
  }

  @Bean
  public DeleteClientPort deleteClientUseCase(ClientPersistencePort clientPersistencePort) {
    return new DeleteClientUseCase(clientPersistencePort);
  }
}
