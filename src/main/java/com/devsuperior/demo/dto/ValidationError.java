package com.devsuperior.demo.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ValidationError extends StandartError {

    private Set<FieldMessage> errors = new HashSet<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public Set<FieldMessage> getErrors() {
        return errors;
    }

    public void addFieldMessage(FieldMessage fieldMessage) {
        errors.add(fieldMessage);
    }


}

