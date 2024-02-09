package com.devsu.adapters.in;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovementResponse {
  private Integer id;
  private LocalDate fecha;
  private String tipo;
  private String numeroCuenta;
  private String nombre;
  private BigDecimal valor;
  private BigDecimal saldo;
  private BigDecimal saldoInicial;
  private Boolean estado;

}
