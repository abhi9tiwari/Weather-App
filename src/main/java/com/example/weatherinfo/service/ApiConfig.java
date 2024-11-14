package com.example.weatherinfo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiConfig {
	
	@Value("${openweather.api.key}")
    private String openWeatherApiKey;

    @Value("${geocoding.api.url}")
    private String geocodingApiUrl;

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    public String getOpenWeatherApiKey() {
        return openWeatherApiKey;
    }

    public String getGeocodingApiUrl() {
        return geocodingApiUrl;
    }

    public String getWeatherApiUrl() {
        return weatherApiUrl;
    }
}
