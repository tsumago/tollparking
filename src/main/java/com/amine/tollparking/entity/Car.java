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

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Car plate is required.")
    @Basic(optional = false)
	private String plateNumber;
	
	@ManyToOne
	private Client owner;
	
	@Enumerated(EnumType.STRING)
	private CarType type;
	
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
	
	public Client getOwner() {
		return owner;
	}
	public void setOwner(Client owner) {
		this.owner = owner;
	}
	public CarType getType() {
		return type;
	}
	public void setType(CarType carType) {
		this.type = carType;
	}
}
