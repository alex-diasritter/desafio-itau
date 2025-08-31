package com.alex.desafio_itau.entity;
import java.time.OffsetDateTime;

public class TransacaoEntity {

    private Long valor;
    private OffsetDateTime dataHora;
    private Integer id;

    public TransacaoEntity(Long valor, OffsetDateTime dataHora, Integer id) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.id = id;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
