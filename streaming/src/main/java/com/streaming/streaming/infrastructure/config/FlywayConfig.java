package com.streaming.streaming.infrastructure.config;

import com.streaming.streaming.infrastructure.config.properties.FlywayProperties;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FlywayConfig {
    private final FlywayProperties flywayProperties;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource(flywayProperties.getUrl(), flywayProperties.getUsername(), flywayProperties.getPassword())
                .load();
    }
}
