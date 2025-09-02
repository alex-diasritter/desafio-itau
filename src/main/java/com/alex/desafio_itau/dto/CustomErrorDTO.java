package com.alex.desafio_itau.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;

// O nome pode continuar o mesmo, pois ele é a base para todos os erros customizados
@JsonInclude(JsonInclude.Include.NON_NULL) // IMPORTANTE: Oculta campos nulos no JSON
public class CustomErrorDTO {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

    // Este campo só aparecerá no JSON se houver erros de validação
    private List<FieldMessageDTO> fieldErrors;

    // Construtor para erros gerais (sem lista de campos)
    public CustomErrorDTO(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    // Getters
    public Instant getTimestamp() { return timestamp; }
    public Integer getStatus() { return status; }
    public String getError() { return error; }
    public String getPath() { return path; }
    public List<FieldMessageDTO> getFieldErrors() { return fieldErrors; }

    // Setter para os erros de campo
    public void setFieldErrors(List<FieldMessageDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public void addError(String field, String defaultMessage) {
    }
}
