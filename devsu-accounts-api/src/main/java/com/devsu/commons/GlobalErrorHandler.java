package com.devsu.commons;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ApiErrorMessage> handleGlobalError(Exception ex){

    if(ex instanceof ApiException){
        ApiException apiException = (ApiException) ex;
        log.error("Error de aplicacion ", ex);
        return ResponseEntity.status(apiException.getStatus())
                .body(ApiErrorMessage.builder()
                        .code(apiException.getCode())
                        .message(apiException.getMessage()).build());
    }
    else if(ex instanceof MethodArgumentNotValidException) {
      log.error("Argumentos invalidos ", ex);
      MethodArgumentNotValidException argError= (MethodArgumentNotValidException) ex;
      BindingResult result= argError.getBindingResult();
      List<FieldError> fieldErrors= result.getFieldErrors();
      StringBuilder errorMessage= new StringBuilder();
      fieldErrors.forEach(error ->
              errorMessage.append("Argumento ["+ error.getField() + "]: " + error.getDefaultMessage() + ". "));
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(ApiErrorMessage.builder().code("GLB01").message(errorMessage.toString()).build());
    }

    log.error("Error general ", ex);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiErrorMessage.builder().code("GLB01").message("Error General").build());
  }
}
