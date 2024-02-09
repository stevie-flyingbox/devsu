package com.devsu.adapters.in;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class MovementRequest {
  private Integer clientId;
  private Integer accountId;
  private String tipo;
  private BigDecimal valor;

}
