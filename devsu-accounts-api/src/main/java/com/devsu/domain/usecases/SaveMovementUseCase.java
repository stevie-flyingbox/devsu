package com.devsu.domain.usecases;

import com.devsu.adapters.in.MovementRequest;
import com.devsu.adapters.in.MovementResponse;
import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.models.Client;
import com.devsu.domain.ports.in.SaveMovementPort;
import com.devsu.domain.ports.out.AccountPersistence;
import com.devsu.domain.ports.out.AccountPersistencePort;
import com.devsu.domain.ports.out.ClientPort;
import com.devsu.domain.ports.out.MovementPersistence;
import com.devsu.domain.ports.out.MovementPersistencePort;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class SaveMovementUseCase implements SaveMovementPort {

  private final ClientPort clientPort;
  private final AccountPersistencePort accountPersistencePort;
  private final MovementPersistencePort movementPersistencePort;

  @Override
  public MovementResponse execute(MovementRequest inputCommand) {
    return Optional.of(inputCommand)
            .map(validateClient())
            .map(validateAccount(inputCommand))
            .map(validateAccountCredit(inputCommand))
            .map(saveMovementAndUpdateAccount(inputCommand))
            .map(buildMovementResponse())
            .get();
  }

  private Function<AccountPersistence, AccountPersistence> validateAccountCredit(MovementRequest request) {
    return accountPersistence -> {
      BigDecimal futureCredit=accountPersistence.getSaldoInicial().add(request.getValor());
      if(futureCredit.compareTo(BigDecimal.ZERO) < 0)
        throw new ApiException(HttpStatus.BAD_REQUEST, ErrorsEnum.CREDIT_UNAVAILABLE);

      return accountPersistence;
    };
  }
  private Function<MovementRequest, Client> validateClient(){
    return movementRequest -> Optional.of(movementRequest.getClientId())
            .map(clientId -> clientPort.getClient(clientId))
            .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorsEnum.CLIENT_NOT_FOUND));
  }

  private Function<Client, AccountPersistence> validateAccount(MovementRequest inputCommand){
    return client -> Optional.ofNullable(inputCommand.getAccountId())
            .map(accountId -> accountPersistencePort.getAccountById(client.getId(), accountId))
            .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, ErrorsEnum.INVALID_ACCOUNT));
  }

  @Transactional
  private Function<AccountPersistence, MovementPersistence> saveMovementAndUpdateAccount(MovementRequest request) {
    return accountPersistence -> Optional.of(accountPersistence)
            .map(saveMovement(request))
            .map(updateAccountCredit(accountPersistence))
            .get();
  }

  private Function<MovementPersistence, MovementPersistence> updateAccountCredit(AccountPersistence accountPersistence){
    return movement -> Optional.of(accountPersistencePort.saveAccount(accountPersistence
                .toBuilder()
                .saldoInicial(movement.getSaldo())
                .build()))
            .map(notused -> movement)
            .get();
  }

  private Function<AccountPersistence, MovementPersistence> saveMovement(MovementRequest request) {
    return accountPersistence -> Optional.of(accountPersistence)
            .map(buildMovementPersistence(request))
            .map(movement -> movementPersistencePort.saveMovement(movement))
            .orElseThrow(() -> new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorsEnum.SAVE_MOVEMENT_ERROR));

  }

  private Function<AccountPersistence, MovementPersistence> buildMovementPersistence(MovementRequest request) {
    return accountPersistence -> {
      BigDecimal newCredit= accountPersistence.getSaldoInicial().add(request.getValor());
      return MovementPersistence.builder()
              .fecha(LocalDate.now())
              .tipo(accountPersistence.getTipo())
              .saldo(newCredit)
              .saldoInicial(accountPersistence.getSaldoInicial())
              .valor(request.getValor())
              .cuenta(accountPersistence.toBuilder().saldoInicial(newCredit).build())
              .build();
    };
  }

  private Function<MovementPersistence, MovementResponse> buildMovementResponse(){
    return movementPersistence -> MovementResponse.builder()
            .id(movementPersistence.getId())
            .fecha(movementPersistence.getFecha())
            .valor(movementPersistence.getValor())
            .saldo(movementPersistence.getSaldo())
            .tipo(movementPersistence.getTipo())
            .build();
  }
}
