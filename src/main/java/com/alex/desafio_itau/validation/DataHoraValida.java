package com.alex.desafio_itau.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DataHoraValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataHoraValida {

    // Mensagem de erro padrão
    String message() default "A transação não pode ocorrer no futuro.";

    // Para agrupar validações (geralmente não é alterado)
    Class<?>[] groups() default {};

    // Para carregar metadados (geralmente não é alterado)
    Class<? extends Payload>[] payload() default {};
}
