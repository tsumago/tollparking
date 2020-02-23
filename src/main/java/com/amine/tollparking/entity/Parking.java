package com.amine.tollparking.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Parking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(mappedBy="parking")
	private Set<Spot> spot;
	
	private String billingPolicy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Spot> getSpot() {
		return spot;
	}

	public void setSpot(Set<Spot> spot) {
		this.spot = spot;
	}

	public String getBillingPolicy() {
		return billingPolicy;
	}

	public void setBillingPolicy(String billingPolicy) {
		this.billingPolicy = billingPolicy;
	}
}
