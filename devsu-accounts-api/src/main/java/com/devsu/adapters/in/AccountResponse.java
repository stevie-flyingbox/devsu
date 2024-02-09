package com.devsu.adapters.in;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccountResponse {
  private Integer id;
  private String numero;
  private String tipo;
  private BigDecimal saldoInicial;
  private Boolean estado;
}
