package com.amine.tollparking.entity;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Parking {
	@Id
	@GeneratedValue
	private long id;

	@OneToMany(mappedBy = "parking", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Spot> spot;

	@NotNull(message = "Billing formula is required.")
	@Basic(optional = false)
	private String billingPolicy;

	public Parking() {
	}

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

		for (Spot child : spot) {
			child.setParking(this);
		}
	}

	public String getBillingPolicy() {
		return billingPolicy;
	}

	public void setBillingPolicy(String billingPolicy) {
		this.billingPolicy = billingPolicy;
	}
}
