package com.alex.desafio_itau.service;
import com.alex.desafio_itau.domain.dto.TransacaoRequestDTO;
import com.alex.desafio_itau.domain.dto.TransacaoResponseDTO;
import com.alex.desafio_itau.domain.entity.StatisticBucket;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransacaoService {

    private static Map<Long, StatisticBucket> buckets = new ConcurrentHashMap<>();

    public StatisticBucket saveTransfers(TransacaoRequestDTO dto) {
        OffsetDateTime odt = OffsetDateTime.parse(dto.getDataHora());
        long currentEpochSecond = OffsetDateTime.now(ZoneOffset.UTC).toEpochSecond();
        long transactionEpochSecond = odt.toEpochSecond();

        if (odt.isAfter(OffsetDateTime.now().plusSeconds(5))) {
            throw new IllegalArgumentException("A dataHora da transação não pode estar no futuro.");
        }

        BigDecimal valor = dto.getValor();

        buckets.compute(transactionEpochSecond, (key, bucket) -> {
            if (bucket == null) {
                return new StatisticBucket(valor);
            } else {
                bucket.add(valor);
                return bucket;
            }
        });
        return null;
    }

    public void deleteAll() {
        buckets.clear();
    }

    public TransacaoResponseDTO getEstatisticas() {
        long currentSecond = OffsetDateTime.now(ZoneOffset.UTC).toEpochSecond();
        StatisticBucket finalStats = new StatisticBucket(BigDecimal.ZERO);
        finalStats.sum = BigDecimal.ZERO;
        finalStats.count = 0;

        // Itera sobre os últimos 60 segundos (janela de tempo)
        for (int i = 0; i < 60; i++) {
            long targetSecond = currentSecond - i;
            StatisticBucket bucket = buckets.get(targetSecond);
            if (bucket != null) {
                finalStats.combine(bucket);
            }
        }

        if (finalStats.getCount() == 0) {
            return new TransacaoResponseDTO(0, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        return new TransacaoResponseDTO(
                (int) finalStats.getCount(),
                finalStats.getSum(),
                finalStats.getAvg(),
                finalStats.getMin(),
                finalStats.getMax()
        );
    }
}