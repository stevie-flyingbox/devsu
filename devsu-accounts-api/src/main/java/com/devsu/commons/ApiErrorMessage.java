package com.devsu.commons;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiErrorMessage {

  private String code;
  private String message;
}
