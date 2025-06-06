package com.ai.spring_llm_agent.functions;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;

public class WeatherService implements Function<WeatherService.Request, WeatherService.Response> {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    private final WeatherConfigProperties weatherProps;
    private final RestClient restClient;

    public WeatherService(WeatherConfigProperties weatherProps) {
        this.weatherProps = weatherProps;
        log.info("Weather API URL: {}", weatherProps.apiUrl());
        log.info("Weather API Key: {}", weatherProps.apiKey());
        this.restClient = RestClient.create(weatherProps.apiUrl());
    }

    @Override
    public Response apply(Request request) {
        log.info("Weather Request : {}", request);
        Response response = restClient.get()
                .uri("/current.json?key={key}&q={q}&aqi=no", weatherProps.apiKey(), request.city())
                .retrieve()
                .body(Response.class);
        log.info("Weather API Response", response);
        return response;
    };

    public record Request(String city) {
    };

    public record Response(Location location, Current current) {
    };

    public record Location(String name, String region, String country, long lat, long lon) {
    };

    public record Current(String temp_f, Condition condition, String wind_mph, String humidity) {
    };

    public record Condition(String text) {
    }

}