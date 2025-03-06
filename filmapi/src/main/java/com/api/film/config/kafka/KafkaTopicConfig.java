package com.api.film.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Description: Set up the topics in the kafka broker
 */
@Configuration
public class KafkaTopicConfig {

    @Value(value = "${spring.kafka.topic-return}")
    private String topicNameToReturn;

    @Value(value = "${spring.kafka.topic-rent}")
    private String topicNameToRent;

    /**
     * Description: Bean to create the topic to returned films
     * <p>
     * test jenkins to the meeting two
     *
     * @return new topic named returned-film
     */
    @Bean
    public NewTopic returnedFilmTopic() {
        return TopicBuilder.name(topicNameToReturn).build();
    }

    /**
     * Description: Bean to create the topic to rent a film
     *
     * @return new topic named rent-film
     */
    @Bean
    public NewTopic rentFilmTopic() {
        return TopicBuilder.name(topicNameToRent).build();
    }
}
