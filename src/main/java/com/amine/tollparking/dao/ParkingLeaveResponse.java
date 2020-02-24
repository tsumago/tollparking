package com.amine.tollparking.dao;

public class ParkingLeaveResponse {
	private double timeParked;
	private double balance;
	
	public ParkingLeaveResponse(double timeParked, double balance) {
		this.timeParked = timeParked;
		this.balance = balance;
	}
	
	public double getTimeParked() {
		return timeParked;
	}
	public void setTimeParked(long timeParked) {
		this.timeParked = timeParked;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
}
