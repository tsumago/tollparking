package com.amine.tollparking.entity;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="SPOT")
public class Spot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Car car;
	
	@ManyToOne
	@NotNull(message = "A spot must be linked to a parking.")
	@JsonBackReference
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
