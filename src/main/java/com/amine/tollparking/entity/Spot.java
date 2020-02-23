package com.amine.tollparking.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Spot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private Car car;
	
	@ManyToOne(fetch = FetchType.LAZY)
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
