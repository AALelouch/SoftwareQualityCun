package com.movies.commonstomovies.calculatetime;

public class CalculateMonthsFactory implements CalculateTimeFactory{

    public CalculateMonthsFactory() {
    }

    @Override
    public CalculateTime create() {
        return new CalculateMonths();
    }
}
