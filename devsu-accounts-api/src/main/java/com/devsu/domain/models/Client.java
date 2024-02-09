package com.devsu.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {
  private Integer id;
  private String nombre;
}
