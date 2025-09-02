package com.alex.desafio_itau.domain.dto;
import com.alex.desafio_itau.validation.DataHoraValida;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransacaoRequestDTO {

    @Positive
    @NotNull
    private BigDecimal valor;

    @DataHoraValida
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
