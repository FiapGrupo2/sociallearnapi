package com.fiap.sociallearn.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiErrorException extends RuntimeException {
  private final HttpStatus status;
  private final String message;

  public ApiErrorException(HttpStatus status, String message) {
    super();
    this.status = status;
    this.message = message;
  }
}
