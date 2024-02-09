package com.devsu.domain.ports.out;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClientPersistence {
  private Integer id;
  private Integer idPersona;
  private String nombre;
  private String genero;
  private Integer edad;
  private String identificacion;
  private String direccion;
  private String telefono;
  private String password;
  private Boolean estado;

}
