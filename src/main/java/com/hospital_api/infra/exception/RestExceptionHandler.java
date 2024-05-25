package com.hospital_api.infra.exception;

import com.hospital_api.domain.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity argumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(FieldExceptionData::new));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity badCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionData("Invalid credentials!"));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity authenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionData( "Invalid authentication! Check fields and try again!"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity accessDeniedException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionData("Access denied!"));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity validationException(ValidationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionData(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity runtimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionData(e.getLocalizedMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionData(ex.getLocalizedMessage()));
    }

    private record ExceptionData(
            String error
    ) {
    }

    private record FieldExceptionData(
            String field,
            String message
    ) {
        public FieldExceptionData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
