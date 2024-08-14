package com.devsuperior.demo.controllers.handler;

import com.devsuperior.demo.dto.FieldMessage;
import com.devsuperior.demo.dto.StandartError;
import com.devsuperior.demo.dto.ValidationError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartError> validationHandling(MethodArgumentNotValidException e,
                                                            HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError standartError = new ValidationError(Instant.now(), status.value(),
                "Dados Invalidos", request.getRequestURI());
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            standartError.addFieldMessage(new FieldMessage(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return ResponseEntity.status(status).body(standartError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandartError> validationHandling(EntityNotFoundException e,
                                                            HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        StandartError standartError = new ValidationError(Instant.now(), status.value(),
                e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standartError);
    }


}
