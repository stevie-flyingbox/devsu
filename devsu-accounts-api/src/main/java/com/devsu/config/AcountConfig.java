package com.devsu.config;

import com.devsu.domain.ports.in.CreateAccountPort;
import com.devsu.domain.ports.in.DeleteAccountPort;
import com.devsu.domain.ports.in.GetAccountPort;
import com.devsu.domain.ports.in.UpdateAccountPort;
import com.devsu.domain.ports.out.AccountPersistencePort;
import com.devsu.domain.ports.out.ClientPort;
import com.devsu.domain.usecases.CreateAccountUseCase;
import com.devsu.domain.usecases.DeleteAccountUseCase;
import com.devsu.domain.usecases.GetAccountUseCase;
import com.devsu.domain.usecases.UpdateClientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcountConfig {
  @Bean
  public CreateAccountPort createAccountUseCase(AccountPersistencePort accountPersistencePort,
                                                ClientPort clientPort) {
    return new CreateAccountUseCase(accountPersistencePort, clientPort);
  }

  @Bean
  public GetAccountPort getAccountUseCase(AccountPersistencePort accountPersistencePort) {
    return new GetAccountUseCase(accountPersistencePort);
  }

  @Bean
  public UpdateAccountPort updateAccountUseCase(AccountPersistencePort accountPersistencePort) {
    return new UpdateClientUseCase(accountPersistencePort);
  }

  @Bean
  public DeleteAccountPort deleteAccountUseCase(AccountPersistencePort accountPersistencePort) {
    return new DeleteAccountUseCase(accountPersistencePort);
  }
}
