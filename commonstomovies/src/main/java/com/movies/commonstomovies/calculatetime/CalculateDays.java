package com.movies.commonstomovies.calculatetime;

import java.time.LocalDateTime;

class CalculateDays implements CalculateTime{

    public CalculateDays() {
    }

    @Override
    public Integer calculate(LocalDateTime entry, LocalDateTime output) {

        return output.getDayOfYear() - entry.getDayOfYear() + 1;
    }
}
