package com.alex.desafio_itau.service;
import com.alex.desafio_itau.dto.TransacaoRequestDTO;
import com.alex.desafio_itau.dto.TransacaoResponseDTO;
import com.alex.desafio_itau.entity.TransacaoEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    }

    private static void exibirTransacoesConsole() {
        System.out.println("\nTransações realizadas: ");
        transacoes.forEach((id, transacaoEntity) -> {
            System.out.printf("ID: %s, Valor: R$ %.2f, DataHora: %s%n",
                    transacaoEntity.getId(),
                    transacaoEntity.getValor(),
                    transacaoEntity.getDataHora());
        });
    }


    public void deleteAll() {
            transacoes.clear();
        }

    public TransacaoResponseDTO getEstatisticas() {
        TransacaoResponseDTO dto = new TransacaoResponseDTO(cont, sum(), avg(), min(), max());
        return dto;
    }

    public static BigDecimal sum() {
        BigDecimal somaTotal = BigDecimal.ZERO;
        for (Map.Entry<Integer, TransacaoEntity> par : transacoes.entrySet()) {
            somaTotal = somaTotal.add(par.getValue().getValor());
        }
        return somaTotal;
    }

    public static BigDecimal avg() {
        if (transacoes.isEmpty()){
            return BigDecimal.ZERO;
        }
        BigDecimal somaTotal = BigDecimal.ZERO;
        for (Map.Entry<Integer, TransacaoEntity> par : transacoes.entrySet()) {
            somaTotal = somaTotal.add(par.getValue().getValor());
        }
        return somaTotal.divide(BigDecimal.valueOf(transacoes.size()), RoundingMode.HALF_UP);
    }

    public static BigDecimal min() {
        BigDecimal menor = null;
        for (Map.Entry<Integer, TransacaoEntity> par : transacoes.entrySet()) {
            BigDecimal valor = par.getValue().getValor();
            if (menor == null || valor.compareTo(menor) < 0) {
                menor = valor;
            }
        }
        return menor;
    }

    public static BigDecimal max() {
        BigDecimal maior = BigDecimal.ZERO;
        for (Map.Entry<Integer, TransacaoEntity> par : transacoes.entrySet()) {
            BigDecimal valor = par.getValue().getValor();
            if (maior == null || valor.compareTo(maior) > 0) {
                maior = valor;
            }
        }
        return maior;
    }


}
