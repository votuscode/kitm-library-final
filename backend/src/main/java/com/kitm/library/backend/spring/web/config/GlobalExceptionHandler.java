package com.kitm.library.backend.spring.web.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.List;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 18.02.22
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiError<String>> handleBadCredentialsException(HttpServletRequest request, BadCredentialsException exception) {
    log.error("BadCredentialsException {}\n", request.getRequestURI(), exception);

    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(new ApiError<>("Bad credentials", List.of(exception.getMessage())));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiError<String>> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException exception) {
    log.error("AccessDeniedException {}\n", request.getRequestURI(), exception);

    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body(new ApiError<>("Access denied!", List.of(exception.getMessage())));
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ApiError<String>> handleValidationException(HttpServletRequest request, ValidationException exception) {
    log.error("ValidationException {}\n", request.getRequestURI(), exception);

    return ResponseEntity
        .badRequest()
        .body(new ApiError<>("Validation error", List.of(exception.getMessage())));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError<String>> handleInternalServerError(HttpServletRequest request, Exception exception) {
    log.error("Exception {}\n", request.getRequestURI(), exception);

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ApiError<>("Internal server error", List.of(exception.getMessage())));
  }

  @Data
  @AllArgsConstructor
  public static class ApiError<T> {
    private String message;
    private List<T> details;
  }
}
