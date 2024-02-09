package com.devsu.adapters.out;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {

  @Query(value = "Select m from MovementEntity m " +
          "join fetch m.cuenta " +
          "Where m.cuenta.idCuenta in (:accountIds) " +
          "and m.fecha Between :from and :to " +
          "order by m.fecha desc")
  List<MovementEntity> findMovementsForAccounts(List<Integer> accountIds, LocalDate from, LocalDate to);
}
