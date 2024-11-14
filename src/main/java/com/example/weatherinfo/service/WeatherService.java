package com.example.weatherinfo.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.weatherinfo.entity.Pincode;
import com.example.weatherinfo.entity.WeatherData;
import com.example.weatherinfo.repository.PincodeRepository;
import com.example.weatherinfo.repository.WeatherDataRepository;

import lombok.SneakyThrows;

@Service
public class WeatherService {

	@Autowired
	PincodeRepository pincodeRepository;

	@Autowired
	WeatherDataRepository weatherDataRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ApiConfig apiConfig;

	@SneakyThrows
	public String getWeather(String pincode, String for_date) {

		// converting String to LocalDate
		LocalDate date = LocalDate.parse(for_date, DateTimeFormatter.ISO_DATE);

		// checking if data already exists for given pincode and date
		Optional<WeatherData> weatherDataOptional = weatherDataRepository.findByPincodeAndDate(pincode, date);
		if (weatherDataOptional.isPresent()) {
			return weatherDataOptional.get().getWeatherJson();
		}

		// get latitude and longitude
		Pincode pincodeEntity = pincodeRepository.findById(pincode).orElseGet(() -> {
			// fetch from Geocoding API
			String countryCode = "IN";
			String geocodingUrl = String.format("%s?zip=%s,%s&appid=%s",
			        apiConfig.getGeocodingApiUrl(),
			        pincode,
			        countryCode,
			        apiConfig.getOpenWeatherApiKey());
			
			GeocodingResponse geocodingResponse = restTemplate.getForObject(geocodingUrl, GeocodingResponse.class);
			if (geocodingResponse == null || geocodingResponse.getLat() == null || geocodingResponse.getLon() == null) {
				throw new RuntimeException("Invalid Pincode or Geocoding API error");
			}
			Pincode newPincode = new Pincode();
			newPincode.setPincode(pincode);
			newPincode.setLatitude(geocodingResponse.getLat());
			newPincode.setLongitude(geocodingResponse.getLon());
			pincodeRepository.save(newPincode);
			return newPincode;
		});

		// fetch weather data from OpenWeather API
		long timestamp = date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();

		String weatherUrl = String.format("%s?lat=%s&lon=%s&dt=%s&appid=%s", apiConfig.getWeatherApiUrl(),
				pincodeEntity.getLatitude(), pincodeEntity.getLongitude(), timestamp, apiConfig.getOpenWeatherApiKey());

		String weatherJson = restTemplate.getForObject(weatherUrl, String.class);
		if (weatherJson == null) {
			throw new RuntimeException("Weather API error");
		}

		// save weather data to db
		WeatherData weatherData = new WeatherData();
		weatherData.setPincode(pincode);
		weatherData.setDate(date);
		weatherData.setWeatherJson(weatherJson);
		weatherDataRepository.save(weatherData);

		return weatherJson;
	}

}
