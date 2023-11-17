package com.m1guelsb.springauth.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidJwtException.class)
  public ResponseEntity<Map<String, List<String>>> handleJwtErrors(InvalidJwtException ex) {

    List<String> errors = List.of(ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorsMap(errors));
  }

  private Map<String, List<String>> errorsMap(List<String> errors) {
    Map<String, List<String>> errorResponse = new HashMap<>();
    errorResponse.put("errors", errors);
    return errorResponse;
  }

}