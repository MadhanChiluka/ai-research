package com.ai.spring_llm_agent.functions;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "weather")
public record WeatherConfigProperties(String apiKey, String apiUrl) {

}
