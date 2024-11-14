package com.example.weatherinfo.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weatherinfo.entity.WeatherData;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
	public Optional<WeatherData> findByPincodeAndDate(String pincode, LocalDate date);
}