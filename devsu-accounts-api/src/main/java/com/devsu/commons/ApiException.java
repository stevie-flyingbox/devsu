package com.devsu.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiException extends RuntimeException {
  private HttpStatus status;
  private String code;
  private String message;

  public ApiException(HttpStatus status, ErrorsEnum error){
    this.status= status;
    this.code = error.code;
    this.message= error.message;
  }
}
