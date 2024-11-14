package com.example.weatherinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weatherinfo.entity.Pincode;

public interface PincodeRepository extends JpaRepository<Pincode, String> {
	
}
