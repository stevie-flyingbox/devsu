package com.devsu.domain.ports.out;

import java.util.List;

public interface AccountPersistencePort {
  AccountPersistence saveAccount(AccountPersistence client);

  AccountPersistence getAccountById(Integer clientId, Integer accountId);
  List<AccountPersistence> getAccountsByClientId(Integer clientId);

  Void deleteAccount(Integer clientId, Integer accountId);
}
