package com.devsu.adapters.out.config;

import com.devsu.adapters.out.MovementPersistenceImpl;
import com.devsu.adapters.out.MovementRepository;
import com.devsu.domain.ports.out.MovementPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovementPersistenceConfig {
  @Bean
  public MovementPersistencePort createMovementPersistenceImpl(MovementRepository movementRepository) {
    return new MovementPersistenceImpl(movementRepository);
  }
}
