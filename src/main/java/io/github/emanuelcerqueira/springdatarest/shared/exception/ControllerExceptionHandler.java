package io.github.emanuelcerqueira.springdatarest.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> onObjectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {

        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> onMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Error", "One or more fields contain incorrect values.", request.getRequestURI());
        e.getBindingResult().getFieldErrors()
                .stream()
                .forEach(fieldError -> err.addError(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> onConstraintValidationException(ConstraintViolationException e, HttpServletRequest request) {

        ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Error", "One or more fields contain incorrect values.", request.getRequestURI());
        e.getConstraintViolations()
                .stream()
                .forEach(violation -> err.addError(violation.getPropertyPath().toString(), violation.getMessage()));
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
    }


}
