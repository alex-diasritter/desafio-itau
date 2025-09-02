package com.alex.desafio_itau.domain.entity;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class StatisticBucket {

    public BigDecimal sum;
    public long count;
    private BigDecimal min;
    private BigDecimal max;

    public StatisticBucket(BigDecimal amount) {
        this.sum = amount;
        this.count = 1;
        this.min = amount;
        this.max = amount;
    }

    public StatisticBucket() {
    }

    public synchronized void add(BigDecimal amount) {
        this.sum = this.sum.add(amount);
        this.count++;
        this.min = this.min.min(amount);
        this.max = this.max.max(amount);
    }

    public synchronized void combine(StatisticBucket other) {
        this.sum = this.sum.add(other.sum);
        this.count += other.count;
        this.min = this.min.min(other.min);
        this.max = this.max.max(other.max);
    }

    public BigDecimal getSum() { return sum; }
    public long getCount() { return count; }
    public BigDecimal getMin() { return min; }
    public BigDecimal getMax() { return max; }

    public BigDecimal getAvg() {
        if (count == 0) {
            return BigDecimal.ZERO;
        }
        return sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
    }
}