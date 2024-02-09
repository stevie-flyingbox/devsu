package com.devsu.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorsEnum {
  CLIENT_NOT_FOUND("001","Cliente no encontrado"),
  SAVE_ERROR("002", "Error al intentar guardar el cliente"),
  DELETE_ERROR("003", "Error al intentar eliminar cliente");

  String code;
  String message;
}
