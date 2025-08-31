package com.alex.desafio_itau.service;

import com.alex.desafio_itau.dto.TransacaoRequestDTO;
import com.alex.desafio_itau.dto.TransacaoResponseDTO;
import com.alex.desafio_itau.entity.TransacaoEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransacaoService {

    private static final Map<Integer, TransacaoEntity> transacoes = new HashMap<>();

    private static Integer cont = 0;

    public void saveTransfers(TransacaoRequestDTO dto) {
        cont++;
        dto.setId(cont);
        OffsetDateTime odt = OffsetDateTime.parse(dto.getDataHora());
        var entity = new TransacaoEntity(dto.getValor(), odt, dto.getId());
        transacoes.put(cont, entity);
        exibirTransacoesConsole();
        if (!transacoes.containsKey(cont)) {
            throw new RuntimeException();
        }
    }

    private static void exibirTransacoesConsole() {
        System.out.println("\nTransações realizadas: ");
        transacoes.forEach((id, transacaoEntity) -> {
            System.out.println("ID: " + id + ", Valor: " + transacaoEntity.getValor() +
                    ", DataHora: " + transacaoEntity.getDataHora());
        });
    }


    public void deleteAll() {

    }

    public TransacaoResponseDTO getEstatisticas() {
        TransacaoResponseDTO dto = new TransacaoResponseDTO(cont, sum(), avg(), min(), max());
        return dto;
    }

    public Long sum() {
        Long somaTotal = 0L;
        for (Map.Entry<Integer, TransacaoEntity> par : transacoes.entrySet()) {
            somaTotal += par.getValue().getValor();
        }
        return somaTotal;
    }

    public Long avg() {
        Long somaTotal = 0L;
        for (Map.Entry<Integer, TransacaoEntity> par : transacoes.entrySet()) {
            somaTotal += par.getValue().getValor();
        }
        return somaTotal / 2;
    }

    public Long min() {
        Long menor = 0L;
        for (Map.Entry<Integer, TransacaoEntity> par : transacoes.entrySet()) {
            if (menor <= par.getValue().getValor()) {
                menor = par.getValue().getValor();
            }
        }
        return menor;
    }

    public Long max() {
        Long maior = 0L;
        for (Map.Entry<Integer, TransacaoEntity> par : transacoes.entrySet()) {
            if (maior >= par.getValue().getValor()) {
                maior = par.getValue().getValor();
            }
        }
        return maior;
    }


}
