package com.alex.desafio_itau.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DataHoraValidator implements ConstraintValidator<DataHoraValida, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        OffsetDateTime datetime = OffsetDateTime.parse(value);
        Instant nowUtc = Instant.now();
        return datetime.toInstant().toEpochMilli() < nowUtc.toEpochMilli();    }
}
