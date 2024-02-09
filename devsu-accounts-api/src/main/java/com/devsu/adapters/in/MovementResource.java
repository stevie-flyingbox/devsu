package com.devsu.adapters.in;

import com.devsu.domain.commands.GetMovementsCommand;
import com.devsu.domain.ports.in.GetMovementsPort;
import com.devsu.domain.ports.in.SaveMovementPort;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
@AllArgsConstructor
public class MovementResource {
  private final SaveMovementPort saveMovementPort;
  private final GetMovementsPort getMovementsPort;

  private static final String DATE_FROM = "#{T(java.time.LocalDate).now().minusMonths(1)}";
  private static final String DATE_TO = "#{T(java.time.LocalDate).now()}";

  @PostMapping
  public ResponseEntity<MovementResponse> saveMovement (@Valid @RequestBody MovementRequest request){
    return ResponseEntity.of(Optional.of(saveMovementPort.execute(request)));
  }

  @GetMapping("/reportes")
  public ResponseEntity<List<MovementResponse>> getMovements(
          @RequestParam(value = "desde", required = false, defaultValue = DATE_FROM)
          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
          @RequestParam(value = "hasta", required = false, defaultValue = DATE_TO)
          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to,
          Integer clientId) {
    return ResponseEntity.of(Optional.of(getMovementsPort.execute(GetMovementsCommand
            .builder()
            .clientId(clientId)
            .from(from)
            .to(to)
            .build())));
  }

}
