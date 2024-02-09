package com.devsu.domain.ports.out;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MovementPersistence {
  private Integer id;
  private LocalDate fecha;
  private String tipo;
  private BigDecimal saldo;
  private BigDecimal saldoInicial;
  private Boolean estado;
  private BigDecimal valor;
  private AccountPersistence cuenta;
}
