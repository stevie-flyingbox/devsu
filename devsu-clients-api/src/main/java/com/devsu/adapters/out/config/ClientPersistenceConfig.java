package com.devsu.adapters.out.config;

import com.devsu.adapters.out.ClientPersistenceImpl;
import com.devsu.adapters.out.ClientRepository;
import com.devsu.domain.ports.out.ClientPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientPersistenceConfig {
  @Bean
  public ClientPersistencePort createClientPersistenceImpl(ClientRepository clientRepository) {
    return new ClientPersistenceImpl(clientRepository);
  }
}
