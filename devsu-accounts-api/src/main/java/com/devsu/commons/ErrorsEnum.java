package com.devsu.commons;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorsEnum {
  CLIENT_NOT_FOUND("001","Cliente no encontrado"),
  SAVE_ERROR("002", "Error al intentar guardar el cliente"),
  DELETE_ERROR("003", "Error al intentar eliminar cliente"),
  INVALID_ACCOUNT("004", "Cuenta invalida"),
  MOVEMENT_NOT_FOUND("005", "No se encuentran movimientos"),
  GET_MOVEMENT_ERROR("006", "No se pueden obtener movimientos"),
  SAVE_MOVEMENT_ERROR("007", "Error al registrar movimiento"),
  CREDIT_UNAVAILABLE("008", "Saldo no disponible");
  String code;
  String message;
}
