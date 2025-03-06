package com.api.film.config.kafka.consumer;

import com.api.film.kafka.KafkaReturnListener;
import com.movies.commonstomovies.kafka.dto.ReturnFilm;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @see KafkaReturnListener
 * Description: Set up the consumer factory and the bean container factory
 * * to the listeners related with consume the messages from returned-film
 */

@EnableKafka
@Configuration
public class KafkaUnassignConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Description: Create a map for the consumer factory
     * setting the server(localhost:9092) and string deserializer.
     *
     * @return HashMap with the mentioned properties
     */
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return props;
    }

    /**
     * Description: Create the consumer factory for the bean rentKafkaListenerContainerFactory
     * using the hashmap with the config set in the consumerConfig().
     *
     * @return DefaultKafkaConsumerFactory with the mentioned properties
     */
    @Bean
    public ConsumerFactory<String, ReturnFilm> consumerFactoryUnassign() {
        JsonDeserializer<ReturnFilm> jsonDeserializer = new JsonDeserializer<>(
                ReturnFilm.class, false);

        jsonDeserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
                jsonDeserializer);
    }

    /**
     * Description: Instance the bean for the listeners related with consumes messages from returned-film
     * topic and the configuration is the method consumerFactory()
     *
     * @return a Bean of ConcurrentKafkaListenerContainerFactory used in the mentioned listeners
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<
            String, ReturnFilm>> returnKafkaListenerContainerFactory
    (ConsumerFactory<String, ReturnFilm> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, ReturnFilm> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactoryUnassign());

        return factory;

    }
}
