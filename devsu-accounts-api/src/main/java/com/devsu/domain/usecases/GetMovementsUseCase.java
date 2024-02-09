package com.devsu.domain.usecases;

import com.devsu.adapters.in.MovementResponse;
import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.commands.GetMovementsCommand;
import com.devsu.domain.models.Client;
import com.devsu.domain.ports.in.GetMovementsPort;
import com.devsu.domain.ports.out.AccountPersistence;
import com.devsu.domain.ports.out.AccountPersistencePort;
import com.devsu.domain.ports.out.ClientPort;
import com.devsu.domain.ports.out.MovementPersistence;
import com.devsu.domain.ports.out.MovementPersistencePort;
import jakarta.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Slf4j
public class GetMovementsUseCase implements GetMovementsPort {

  private final MovementPersistencePort movementPersistencePort;
  private final AccountPersistencePort accountPersistencePort;
  private final ClientPort clientPort;
  @Override
  public List<MovementResponse> execute(GetMovementsCommand inputCommand) {
    try{
      Client client= clientPort.getClient(inputCommand.getClientId());
      List<Integer> accountIds= accountPersistencePort.getAccountsByClientId(inputCommand.getClientId())
              .stream().map(AccountPersistence::getId).collect(Collectors.toList());
      List<MovementResponse> movements= movementPersistencePort.getMovements(accountIds, inputCommand.getFrom(),
              inputCommand.getTo()).stream().map(buildMovementResponse()).collect(Collectors.toList());
      if(movements.isEmpty())
        throw new ApiException(HttpStatus.NOT_FOUND, ErrorsEnum.MOVEMENT_NOT_FOUND);

      return addClientInfoToResponse(movements, client);
    }catch (Exception ex){
      log.error("Error al obtener movimientos ", ex);
      if(ex instanceof ApiException) throw ex;
      throw new ApiException(HttpStatus.NOT_FOUND, ErrorsEnum.GET_MOVEMENT_ERROR);
    }
  }

  private List<MovementResponse> addClientInfoToResponse(List<MovementResponse> movements, Client client){
    return  movements.stream()
            .map(movement -> movement.toBuilder().nombre(client.getNombre()).build())
            .collect(Collectors.toList());
  }
  private Function<MovementPersistence, MovementResponse> buildMovementResponse() {
    return movementPersistence -> MovementResponse.builder()
            .id(movementPersistence.getId())
            .tipo(movementPersistence.getTipo())
            .saldo(movementPersistence.getSaldo())
            .saldoInicial(movementPersistence.getSaldoInicial())
            .estado(movementPersistence.getCuenta().getEstado())
            .numeroCuenta(movementPersistence.getCuenta().getNumero())
            .valor(movementPersistence.getValor())
            .fecha(movementPersistence.getFecha())
            .build();
  }
}
