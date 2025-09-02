package com.alex.desafio_itau.domain.entity;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class TransacaoEntity {

    private BigDecimal valor;
    private OffsetDateTime dataHora;
    private Integer id;

    public TransacaoEntity(BigDecimal valor, OffsetDateTime dataHora, Integer id) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }


    public OffsetDateTime getDataHora() {
        return dataHora;
    }

}
