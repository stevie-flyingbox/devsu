package com.devsu.adapters.out;

import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.ports.out.AccountPersistence;
import com.devsu.domain.ports.out.MovementPersistence;
import com.devsu.domain.ports.out.MovementPersistencePort;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Slf4j
public class MovementPersistenceImpl implements MovementPersistencePort {

private final MovementRepository movementRepository;
  @Override
  public MovementPersistence saveMovement(MovementPersistence movement) {
    try{
      MovementEntity movementEntity = movementRepository.save(buildMovementEntity(movement));
      return buildPersistenceResponse(movementEntity);
    }
    catch (Exception e){
      log.error("Error al guardar movimiento", e);
      throw new ApiException(HttpStatus.CONFLICT, ErrorsEnum.SAVE_MOVEMENT_ERROR);
    }
  }

  @Override
  public List<MovementPersistence> getMovements(List<Integer> accountIds, LocalDate from, LocalDate to) {
    try{
      List<MovementEntity> movements = movementRepository.findMovementsForAccounts(accountIds, from, to);

      return movements.stream().map(mov -> buildPersistenceResponse(mov))
              .collect(Collectors.toList());
    }
    catch (Exception e){
      log.error("Error al obtener movimientos", e);
      throw new ApiException(HttpStatus.NOT_FOUND, ErrorsEnum.GET_MOVEMENT_ERROR);
    }
  }

  private MovementEntity buildMovementEntity(MovementPersistence movementPersistence) {
    return MovementEntity.builder()
            .tipo(movementPersistence.getTipo())
            .fecha(movementPersistence.getFecha())
            .saldo(movementPersistence.getSaldo())
            .valor(movementPersistence.getValor())
            .cuenta(buildAccountEntity(movementPersistence.getCuenta()))
            .saldoInicial(movementPersistence.getSaldoInicial())
            .build();
  }

  private AccountEntity buildAccountEntity(AccountPersistence accountPersistence){
    return AccountEntity.builder()
            .idCuenta(accountPersistence.getId())
            .idCliente(accountPersistence.getClientId())
            .estado(accountPersistence.getEstado())
            .saldoInicial(accountPersistence.getSaldoInicial())
            .numero(accountPersistence.getNumero())
            .build();
  }
  private MovementPersistence buildPersistenceResponse(MovementEntity entity) {
    return MovementPersistence.builder()
            .id(entity.getIdMovimiento())
            .valor(entity.getValor())
            .cuenta(AccountPersistence.builder()
                    .numero(entity.getCuenta().getNumero())
                    .estado(entity.getCuenta().getEstado()).build())
            .saldo(entity.getSaldo())
            .saldoInicial(entity.getSaldoInicial())
            .tipo(entity.getTipo())
            .fecha(entity.getFecha())
            .build();
  }
}
