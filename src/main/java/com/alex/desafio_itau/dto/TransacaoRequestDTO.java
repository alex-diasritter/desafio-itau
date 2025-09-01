package com.alex.desafio_itau.dto;

import java.math.BigDecimal;

public class TransacaoRequestDTO {

    private BigDecimal valor;
    private String dataHora;
    private Integer id;

    public TransacaoRequestDTO(BigDecimal valor, String dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
