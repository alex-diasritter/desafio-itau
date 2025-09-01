package com.alex.desafio_itau.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

public class DataHoraValidator implements ConstraintValidator<DataHoraValida, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            OffsetDateTime.parse(value);
        } catch (DateTimeParseException e) {
            throw new
        }
        return true;
    }

}
