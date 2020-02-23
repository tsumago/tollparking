package com.amine.tollparking.entity;

import java.util.Date;

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
	private Parking parking;

	private Date entranceTime;
	
	@Enumerated(EnumType.STRING)
	private CarType spotType;

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

	public Date getEntranceTime() {
		return entranceTime;
	}

	public void setEntranceTime(Date entranceTime) {
		this.entranceTime = entranceTime;
	}

	public Date getEntertime() {
		return entranceTime;
	}

	public void setEntertime(Date entranceTime) {
		this.entranceTime = entranceTime;
	}

	public CarType getSpotType() {
		return spotType;
	}

	public void setSpotType(CarType spotType) {
		this.spotType = spotType;
	}
}
