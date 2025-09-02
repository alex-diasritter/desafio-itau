package com.alex.desafio_itau.controller;
import com.alex.desafio_itau.dto.CustomErrorDTO;
import com.alex.desafio_itau.dto.FieldMessageDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorDTO> httpMessageNotReadable(HttpMessageNotReadableException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), "O corpo da requisição está inválido. Verifique a sintaxe do JSON.", request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorDTO> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorDTO err = new CustomErrorDTO(Instant.now(), status.value(), "Erro de validação", request.getRequestURI());
        List<FieldMessageDTO> validationErrors = new ArrayList<>();
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            validationErrors.add(new FieldMessageDTO(f.getField(), f.getDefaultMessage()));
        }
        err.setFieldErrors(validationErrors);
        return ResponseEntity.status(status).body(err);
    }

}
