package com.example.weatherinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weatherinfo.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Autowired
	WeatherService weatherService;
	
	@GetMapping
	public String getWeather(@RequestParam String pincode, @RequestParam String for_date) {
		try {
			return weatherService.getWeather(pincode, for_date);
		}catch(Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}
}
