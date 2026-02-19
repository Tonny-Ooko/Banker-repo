package com.bank.masking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Configuration
@EnableConfigurationProperties(MaskingProperties.class)
@ConditionalOnProperty(prefix = "p11.masking", name = "enabled", havingValue = "true")
public class MaskingAutoConfiguration {

    @Bean
    public LoggingMaskingAspect loggingMaskingAspect(MaskingProperties props) {
        return new LoggingMaskingAspect(props);
    }
}
