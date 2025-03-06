package com.practice.clientapi.config.kafka;

import com.movies.commonstomovies.calculatetime.CalculateWeeksFactory;
import com.movies.commonstomovies.kafka.dto.RequestRent;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, ReturnFilm> returnProducerFactory (){

        Map<String,Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);


    }

    @Bean
    public ProducerFactory<String, RequestRent> rentProducerFactory (){

        Map<String,Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);


    }

    @Bean
    public KafkaTemplate<String,ReturnFilm> returnKafkaTemplate(){
        return new KafkaTemplate<>(returnProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, RequestRent> rentKafkaTemplate(){
        return new KafkaTemplate<>(rentProducerFactory());
    }


}