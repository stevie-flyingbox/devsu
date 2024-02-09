package com.devsu.adapters.out.config;

import com.devsu.adapters.out.AccountPersistenceImpl;
import com.devsu.adapters.out.AccountRepository;
import com.devsu.adapters.out.clients.ClientRestImpl;
import com.devsu.domain.ports.out.AccountPersistencePort;
import com.devsu.domain.ports.out.ClientPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountPersistenceConfig {
  @Bean
  public AccountPersistencePort createClientPersistenceImpl(AccountRepository accountRepository) {
    return new AccountPersistenceImpl(accountRepository);
  }

  @Bean
  public ClientPort createClientPort(){
    return new ClientRestImpl();
  }
}
