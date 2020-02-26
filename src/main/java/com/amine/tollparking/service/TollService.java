package com.amine.tollparking.service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.amine.tollparking.dao.ParkingLeaveResponse;
import com.amine.tollparking.entity.Car;
import com.amine.tollparking.entity.Client;
import com.amine.tollparking.entity.Spot;
import com.amine.tollparking.errorhandler.ApiException;
import com.amine.tollparking.repository.CarRepository;
import com.amine.tollparking.repository.ClientRepository;
import com.amine.tollparking.repository.SpotRepository;
import com.google.common.base.Splitter;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Service
public class TollService {
	@Autowired
	private SpotRepository spotRepo;
	
	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Value("${formula}")
	private String formulaProperties;
	
	private final String TIME_PARKED = "timeparked";
	
	public void enterParking(String carPlate, Long parkingId) {
		Car car = carRepo.findByPlateNumber(carPlate);
		
		if (car == null) {
			throw new ApiException("Car with plate number " + carPlate + " is not registered", HttpStatus.NOT_FOUND);
		}
		
		Spot availableSpot = spotRepo.findFirstBySpotTypeAndParkingIdAndCarIdIsNull(car.getType(), parkingId);
		
		if (availableSpot == null) {
			throw new ApiException("No Available spot found", HttpStatus.FORBIDDEN);
		}
		
		availableSpot.setCar(car);
		availableSpot.setEntranceTime(ZonedDateTime.now());
		
		spotRepo.save(availableSpot);
	}
	
	public ParkingLeaveResponse leaveParking(String plateNumber) {
		Car car = carRepo.findByPlateNumber(plateNumber);
		
		if (car == null) {
			throw new ApiException("Car with plate number " + plateNumber + " is not registered", HttpStatus.EXPECTATION_FAILED);
		}
		
		Spot spot = spotRepo.findByCarId(car.getId());
		
		if (spot == null) {
			throw new ApiException("The spot was not set as occupied by plate number " + plateNumber, HttpStatus.EXPECTATION_FAILED);
		}
		
		if (spot.getEntranceTime() == null) {
			throw new ApiException("The car entrance time was not recorded", HttpStatus.EXPECTATION_FAILED);
		}
		
		// Get billing formula
		String formula = spot.getParking().getBillingPolicy();
		
		//Convert the Billing dictionary to list
		//Set the formula values
		Map<String, String> formulaVariables = parseFormulaProperties(formulaProperties); 
		
		//Build the formula
		//TODO Check the formula validity
		Expression e = new ExpressionBuilder(formula).variables(formulaVariables.keySet()).variable(TIME_PARKED).build();
		
		//Set variable values
		formulaVariables.forEach((k, v) -> e.setVariable(k, Double.parseDouble(v)));
		
		//Calculate parking time spent		
		Duration parkingTime = Duration.between(spot.getEntranceTime(), ZonedDateTime.now());
		
		e.setVariable(TIME_PARKED, parkingTime.toHours());
		
		double cost = e.evaluate();
		
		//Charge the client account
		Client client = car.getClient();
		
		double newBalance = client.getBalance() - cost;
		
		client.setBalance(client.getBalance() - cost);
		
		clientRepo.save(client);
		
		//Clearing the spot
		spot.setEntranceTime(null);
		spot.setCar(null);
		
		spotRepo.save(spot);
		
		return new ParkingLeaveResponse(parkingTime.toHours(), newBalance);
	}
	
	private Map<String, String> parseFormulaProperties(String formulaProperties) {
	    return Splitter.on(",").withKeyValueSeparator("=").split(formulaProperties);
	}

}
