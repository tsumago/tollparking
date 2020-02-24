package com.amine.tollparking.entity;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	@NotNull(message = "Username is required.")
    @Basic(optional = false)
	@Column(unique = true)
	private String username;
	
	@NotNull(message = "Balance is required.")
    @Basic(optional = false)
	private double balance;
	
	@OneToMany(mappedBy="client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Car> car;
	
	public Client() {}
	
	public Client(String firstName, String lastName, String username, int balance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.balance = balance;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<Car> getCars() {
		return car;
	}

	public void setCars(Set<Car> car) {
		this.car = car;
	}
}
