package com.amine.tollparking.controller;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amine.tollparking.dao.ParkingLeaveResponse;
import com.amine.tollparking.service.TollService;

@RestController
@RequestMapping("/toll")
@Transactional
public class TollController {
	@Autowired
	private TollService tollService;
	
	private final Logger logger = LoggerFactory.getLogger(TollController.class);
	
	@GetMapping("/enter/{carPlate}-{parkingId}")
	@ResponseStatus(HttpStatus.OK)
	public void enterParking(@PathVariable @NotBlank String carPlate, @PathVariable @NotBlank Long parkingId) {
		logger.info("Car with plate number {} entering parking {}.", carPlate, parkingId);
		tollService.enterParking(carPlate, parkingId);
		logger.info("Car with plate number {} entered successfully to parking {}.", carPlate, parkingId);
	}
	
	@GetMapping("/leave/{carPlate}")
	public ResponseEntity<ParkingLeaveResponse> leaveParking(@PathVariable @NotBlank String carPlate) {
		logger.info("Car with plate number {} leaving parking.", carPlate);
		ParkingLeaveResponse response = tollService.leaveParking(carPlate);
		
		HttpStatus status = HttpStatus.OK;
		
		if (response.getBalance() <= 0) {
			status = HttpStatus.PAYMENT_REQUIRED;
			logger.warn("Car with plate number {} left with a negative balance.", carPlate);
		}
		
		logger.info("Car with plate number {} left successfully.", carPlate);
		
		return ResponseEntity.status(status).body(response);
	}
}
