package com.movies.commonstomovies.calculatetime;

public class CalculateWeeksFactory implements CalculateTimeFactory{

    public CalculateWeeksFactory() {
    }

    @Override
    public CalculateTime create() {
        return new CalculateWeeks();
    }

}
