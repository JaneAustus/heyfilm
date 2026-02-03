package com.enums;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {

        MappingJackson2MessageConverter converter =
                new MappingJackson2MessageConverter();

        // Convert object -> JSON -> TextMessage
        converter.setTargetType(MessageType.TEXT);

        // Required for type mapping
        converter.setTypeIdPropertyName("_type");

        return converter;
    }
}
