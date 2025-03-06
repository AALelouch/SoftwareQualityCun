package com.movies.commonstomovies.calculatetime;

import java.time.LocalDateTime;

class CalculateWeeks implements CalculateTime{

    public CalculateWeeks() {
    }

    @Override
    public Integer calculate(LocalDateTime entry, LocalDateTime output) {

        float weeks = ((float) output.getDayOfYear() - (float) entry.getDayOfYear()) / 7;
        String weeksInString = String.valueOf(weeks);

        if(weeksInString.matches(".0")){
            return (int) weeks;
        }

        if(weeksInString.matches(".*")){
            return (int) weeks + 1;
        }

        return null;

    }
}
