package com.alex.desafio_itau.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

public class DataHoraValidator implements ConstraintValidator<DataHoraValida, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        OffsetDateTime datetime = OffsetDateTime.parse(value);
        return datetime.isBefore(OffsetDateTime.now());
    }
}
