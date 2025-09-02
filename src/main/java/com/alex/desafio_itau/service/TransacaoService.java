package com.alex.desafio_itau.service;
import com.alex.desafio_itau.domain.dto.TransacaoRequestDTO;
import com.alex.desafio_itau.domain.dto.TransacaoResponseDTO;
import com.alex.desafio_itau.domain.entity.TransacaoEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TransacaoService {

    private static final Map<Integer, TransacaoEntity> transacoes = new ConcurrentHashMap<>();
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    public void saveTransfers(TransacaoRequestDTO dto) {
        OffsetDateTime odt;
        try {
            odt = OffsetDateTime.parse(dto.getDataHora());
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de dataHora inválido.");
        }

        if (odt.isAfter(OffsetDateTime.now().plusSeconds(5))) {
            throw new IllegalArgumentException("A dataHora da transação não pode estar no futuro.");
        }

        int newId = idCounter.incrementAndGet();
        dto.setId(newId);

        var entity = new TransacaoEntity(dto.getValor(), odt, dto.getId());
        transacoes.put(newId, entity);
    }

    public void deleteAll() {
        transacoes.clear();
        idCounter.set(0);
    }

    public TransacaoResponseDTO getEstatisticas() {
        final OffsetDateTime timeEnd = OffsetDateTime.now();
        final OffsetDateTime timeStart = timeEnd.minusSeconds(60L);

        List<TransacaoEntity> transacoesNoIntervalo = transacoes.values().stream()
                .filter(t -> {
                    Instant instant = t.getDataHora().toInstant();
                    return !instant.isBefore(timeStart.toInstant()) && !instant.isAfter(timeEnd.toInstant());
                })
                .toList();

        if (transacoesNoIntervalo.isEmpty()) {
            return new TransacaoResponseDTO(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        BigDecimal sum = transacoesNoIntervalo.stream()
                .map(TransacaoEntity::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal avg = sum.divide(
                BigDecimal.valueOf(transacoesNoIntervalo.size()),
                2, // Boa prática definir a precisão
                RoundingMode.HALF_UP
        );

        BigDecimal min = transacoesNoIntervalo.stream()
                .map(TransacaoEntity::getValor)
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        BigDecimal max = transacoesNoIntervalo.stream()
                .map(TransacaoEntity::getValor)
                .max(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);

        return new TransacaoResponseDTO(transacoesNoIntervalo.size(), sum, avg, min, max);
    }
}