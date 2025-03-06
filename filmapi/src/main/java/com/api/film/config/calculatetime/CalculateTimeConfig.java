package com.api.film.config.calculatetime;

import com.movies.commonstomovies.calculatetime.CalculateDaysFactory;
import com.movies.commonstomovies.calculatetime.CalculateTimeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculateTimeConfig {

    @Bean
    public CalculateTimeFactory calculateTimeInDaysFactoryBean(){
        return new CalculateDaysFactory();
    }
}
