package com.devsu.adapters.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewAccountRequest {

  @NotNull
  private Integer clientId;
  @NotBlank
  private String numero;
  @NotBlank
  private String tipo;
  @NotNull
  private BigDecimal saldoInicial;
  @NotNull
  private Boolean estado;
}
