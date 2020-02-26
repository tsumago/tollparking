package com.amine.tollparking.data;

import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.amine.tollparking.entity.Car;
import com.amine.tollparking.entity.CarType;
import com.amine.tollparking.entity.Client;
import com.amine.tollparking.entity.Parking;
import com.amine.tollparking.entity.Spot;
import com.amine.tollparking.repository.ClientRepository;
import com.amine.tollparking.repository.ParkingRepository;

@Component
public class InitData {

    @Autowired
    private ParkingRepository parkingRepo;
    
    @Autowired
    private ClientRepository clientRepo;

    @EventListener
    public void initData(ApplicationReadyEvent event) {
    	// Parking
    	Parking parking = new Parking();
    	
    	parking.setBillingPolicy("minimalfare + timeparked * priceperhour");
    	
    	Spot spot1 = new Spot();
    	spot1.setSpotType(CarType.ELECTRIC20KW);
    	
    	Spot spot2 = new Spot();
    	spot2.setSpotType(CarType.ELECTRIC50KW);
    	
    	Spot spot3 = new Spot();
    	spot3.setSpotType(CarType.STANDARD);
    	
    	Spot spot4 = new Spot();
    	spot4.setSpotType(CarType.STANDARD);
    	
    	parking.setSpot(new HashSet<>(Arrays.asList(spot1,spot2,spot3,spot4)));
    	
    	parkingRepo.save(parking);
    	
    	// Client sample 1
    	Client client = new Client("Amine", "BOULIFA", "tsumago", 100);
    	
    	// Car sample 1
    	Car car = new Car();
    	car.setPlateNumber("H3545V");
    	car.setType(CarType.ELECTRIC50KW);
    	
    	client.setCars(new HashSet<>(Arrays.asList(car)));
    	
    	clientRepo.save(client);
    	
    	// Client sample 2
    	Client client2 = new Client("Jonathan", "DEMERS", "demers", 100);
    	
    	// Car sample 2
    	Car car2 = new Car();
    	car2.setPlateNumber("TESTFFFF");
    	car2.setType(CarType.ELECTRIC20KW);
    	
    	client2.setCars(new HashSet<>(Arrays.asList(car2)));
    	
    	clientRepo.save(client2);
    }
}
