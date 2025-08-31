package com.alex.desafio_itau.dto;

public class TransacaoRequestDTO {

    private Long valor;
    private String dataHora;
    private Integer id;

    public TransacaoRequestDTO(Long valor, String dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
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
