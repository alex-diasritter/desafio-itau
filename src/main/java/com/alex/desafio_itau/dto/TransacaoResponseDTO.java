package com.alex.desafio_itau.dto;

public class TransacaoResponseDTO {

    private int count;
    private Long sum;
    private Long avg;
    private Long min;
    private Long max;

    public TransacaoResponseDTO(int count, Long sum, Long avg, Long min, Long max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Long getAvg() {
        return avg;
    }

    public void setAvg(Long avg) {
        this.avg = avg;
    }

    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }
}
