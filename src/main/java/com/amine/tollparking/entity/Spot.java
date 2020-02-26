package com.amine.tollparking.entity;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Spot {
	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
	private Car car;
	
	@ManyToOne
	@JsonBackReference
	@NotNull(message = "A spot must be linked to a parking.")
	private Parking parking;

	private ZonedDateTime entranceTime;
	
	@Enumerated(EnumType.STRING)
	private CarType spotType;
	
	public Spot() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public ZonedDateTime getEntranceTime() {
		return entranceTime;
	}

	public void setEntranceTime(ZonedDateTime entranceTime) {
		this.entranceTime = entranceTime;
	}

	public CarType getSpotType() {
		return spotType;
	}

	public void setSpotType(CarType spotType) {
		this.spotType = spotType;
	}
}
