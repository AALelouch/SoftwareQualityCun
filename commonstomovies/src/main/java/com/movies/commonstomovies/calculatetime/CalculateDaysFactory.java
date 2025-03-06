package com.movies.commonstomovies.calculatetime;

public class CalculateDaysFactory implements CalculateTimeFactory {

    public CalculateDaysFactory() {
    }

    @Override
    public CalculateTime create() {
        return new CalculateDays();
    }
}
