package com.devsu.domain.ports.out;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class AccountPersistence {
  private Integer id;
  private Integer clientId;
  private String numero;
  private String tipo;
  private BigDecimal saldoInicial;
  private Boolean estado;

}
