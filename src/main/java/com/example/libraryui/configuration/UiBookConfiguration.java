package com.example.libraryui.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;

@Configuration
@EnableConfigurationProperties(ApiCallConfigurationProperties.class)
public class UiBookConfiguration {
    private final ApiCallConfigurationProperties properties;

    public UiBookConfiguration(ApiCallConfigurationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public RestOperations restTemplate(RestTemplateBuilder builder) {
        return new RestTemplateBuilder().requestFactory(HttpComponentsClientHttpRequestFactory.class).build();
    }
}
