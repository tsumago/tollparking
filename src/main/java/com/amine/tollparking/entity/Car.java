package com.amine.tollparking.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Car plate is required.")
    @Basic(optional = false)
	private String plateNumber;
	
	@ManyToOne
	@NotNull(message = "A car must be linked to a client.")
	@JsonBackReference
	private Client client;
	
	@Enumerated(EnumType.STRING)
	private CarType type;
	
	public Car() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public CarType getType() {
		return type;
	}
	public void setType(CarType carType) {
		this.type = carType;
	}
}
