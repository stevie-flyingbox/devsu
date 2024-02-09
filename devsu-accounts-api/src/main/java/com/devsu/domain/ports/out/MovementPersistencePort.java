package com.devsu.domain.ports.out;

import java.time.LocalDate;
import java.util.List;

public interface MovementPersistencePort {
  MovementPersistence saveMovement(MovementPersistence movement);

  List<MovementPersistence> getMovements(List<Integer> accountIds, LocalDate from, LocalDate to);
}
