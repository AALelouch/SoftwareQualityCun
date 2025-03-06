package com.movies.commonstomovies.calculatetime;

import java.time.LocalDateTime;

class CalculateMonths implements CalculateTime{

    public CalculateMonths() {
    }

    @Override
    public Integer calculate(LocalDateTime entry, LocalDateTime output) {
        return output.getMonthValue() - entry.getMonthValue() + 1;
    }
}
