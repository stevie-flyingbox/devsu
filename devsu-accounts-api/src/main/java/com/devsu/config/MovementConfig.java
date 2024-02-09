package com.devsu.config;

import com.devsu.domain.ports.in.GetMovementsPort;
import com.devsu.domain.ports.in.SaveMovementPort;
import com.devsu.domain.ports.out.AccountPersistencePort;
import com.devsu.domain.ports.out.ClientPort;
import com.devsu.domain.ports.out.MovementPersistencePort;
import com.devsu.domain.usecases.GetMovementsUseCase;
import com.devsu.domain.usecases.SaveMovementUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovementConfig {
  @Bean
  public SaveMovementPort saveMovementPort(ClientPort clientPort,
                                           AccountPersistencePort accountPersistencePort,
                                           MovementPersistencePort movementPersistencePort){
    return new SaveMovementUseCase(clientPort, accountPersistencePort, movementPersistencePort);
  }

  @Bean
  public GetMovementsPort getMovementsUseCase(MovementPersistencePort movementPersistencePort,
                                              AccountPersistencePort accountPersistencePort,
                                              ClientPort clientPort){
    return new GetMovementsUseCase(movementPersistencePort, accountPersistencePort, clientPort);
  }
}
