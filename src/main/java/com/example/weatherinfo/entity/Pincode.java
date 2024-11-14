package com.example.weatherinfo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Pincode {
	@Id
	private String pincode;
	private double latitude;
	private double longitude;
}
