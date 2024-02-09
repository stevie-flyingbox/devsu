package com.devsu.adapters.out;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
  Optional<AccountEntity> findByIdClienteAndIdCuenta(Integer clientId, Integer accountId);
  List<AccountEntity> findByIdCliente(Integer clientId);
}
