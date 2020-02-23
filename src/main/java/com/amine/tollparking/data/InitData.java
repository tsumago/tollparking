package com.amine.tollparking.data;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.amine.tollparking.entity.Car;
import com.amine.tollparking.entity.CarType;
import com.amine.tollparking.entity.Client;
import com.amine.tollparking.entity.Parking;
import com.amine.tollparking.entity.Spot;
import com.amine.tollparking.repository.CarRepository;
import com.amine.tollparking.repository.ClientRepository;
import com.amine.tollparking.repository.ParkingRepository;

@Component
public class InitData {

    @Autowired
    private ParkingRepository parkingRepo;
    
    @Autowired
    private ClientRepository clientRepo;
    
    @Autowired
    private CarRepository carRepo;

    @EventListener
    public void initData(ApplicationReadyEvent event) {
    	// Parking
    	Parking parking = new Parking();
    	
    	parking.setBillingPolicy("h * priceHour");
    	
    	Spot spot1 = new Spot();
    	spot1.setSpotType(CarType.ELECTRIC20KW);
    	spot1.setParking(parking);
    	
    	Spot spot2 = new Spot();
    	spot2.setSpotType(CarType.ELECTRIC50KW);
    	spot2.setParking(parking);
    	
    	Spot spot3 = new Spot();
    	spot3.setSpotType(CarType.STANDARD);
    	spot3.setParking(parking);
    	
    	Spot spot4 = new Spot();
    	spot4.setSpotType(CarType.STANDARD);
    	spot4.setParking(parking);
    	
    	Set<Spot> spots = new HashSet<>();
    	
    	Collections.addAll(spots, spot1,spot2,spot3,spot4);

    	parking.setSpot(spots);
    	
    	parkingRepo.save(parking);
    	
    	// Client sample
    	Client client = new Client("Amine", "BOULIFA", "tsumago", 100);
    	
    	clientRepo.save(client);
    	
    	// Car sample
    	Car car = new Car();
    	car.setClient(client);
    	car.setPlateNumber("H3545V");
    	car.setType(CarType.ELECTRIC50KW);
    	
    	carRepo.save(car);
    }
}
