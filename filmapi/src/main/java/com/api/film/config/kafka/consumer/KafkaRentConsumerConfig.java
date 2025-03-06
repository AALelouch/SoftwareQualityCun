package com.api.film.config.kafka.consumer;

import com.api.film.kafka.KafkaRentListener;
import com.movies.commonstomovies.kafka.dto.RequestRent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @see KafkaRentListener
 * Description: Set up the consumer factory and the bean container factory
 * to the listeners related with consume the messages from rent-film
 */

@Configuration
@EnableKafka
public class KafkaRentConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Description: Create the consumer factory for the bean rentKafkaListenerContainerFactory
     * setting the server(localhost:9092), json(RequestRent) and string deserializer.
     *
     * @return DefaultKafkaConsumerFactory with the mentioned properties
     */
    public ConsumerFactory<String, RequestRent> consumerFactory() {

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        JsonDeserializer<RequestRent> jsonDeserializer = new JsonDeserializer<>(RequestRent.class, false);
        jsonDeserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
                jsonDeserializer);
    }

    /**
     * Description: Instance the bean for the listeners related with consumes messages from rent-film
     * topic and the configuration is the method consumerFactory()
     *
     * @return a Bean of ConcurrentKafkaListenerContainerFactory used in the mentioned listeners
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestRent>
    rentKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, RequestRent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }


}
