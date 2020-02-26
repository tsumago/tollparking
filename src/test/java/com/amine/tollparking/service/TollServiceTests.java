package com.amine.tollparking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amine.tollparking.entity.Car;
import com.amine.tollparking.entity.CarType;
import com.amine.tollparking.entity.Client;
import com.amine.tollparking.entity.Spot;
import com.amine.tollparking.errorhandler.ApiException;
import com.amine.tollparking.repository.CarRepository;
import com.amine.tollparking.repository.ClientRepository;
import com.amine.tollparking.repository.SpotRepository;

@ExtendWith(MockitoExtension.class)
public class TollServiceTests {
	@Mock
	private SpotRepository spotRepo;

	@Mock
	private CarRepository carRepo;

	@Mock
	private ClientRepository clientRepo;

	@InjectMocks
	private TollService tollService;
	
	private Car car;
	
	private Client client;
	
	private Spot spot;
	
	@BeforeEach
	private void init() {
		client = new Client("Amine", "Test", "eltore", 100);
		
		car = new Car();
		car.setClient(client);
		car.setId(1L);
		car.setPlateNumber("TEST");
		car.setType(CarType.STANDARD);
		
		spot = new Spot();
		spot.setSpotType(CarType.STANDARD);
	}

	@Test
	public void enterParking_WhenCarNotFound() {
		when(carRepo.findByPlateNumber(anyString())).thenReturn(null);
		ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
			tollService.enterParking("FFFFFF", 1L);
		});

		assertEquals(thrown.getMessage(), "Car with plate number FFFFFF is not registered");
	}
	
	@Test
	public void enterParking_WhenNoSpotAvailable() {
		when(carRepo.findByPlateNumber(anyString())).thenReturn(car);
		when(spotRepo.findFirstBySpotTypeAndParkingIdAndCarIdIsNull(any(CarType.class), anyLong())).thenReturn(null);
		ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
			tollService.enterParking("FFFFFF", 1L);
		});
		
		assertEquals(thrown.getMessage(), "No Available spot found");
	}
	
	@Test
	public void enterParking_WhenHappyPath() {
		when(carRepo.findByPlateNumber(anyString())).thenReturn(car);
		when(spotRepo.findFirstBySpotTypeAndParkingIdAndCarIdIsNull(any(CarType.class), anyLong())).thenReturn(spot);
		when(spotRepo.save(any(Spot.class))).thenReturn(null);
		
		tollService.enterParking("FFFFFF", 1L);
		
		verify(spotRepo, times(1)).save(any(Spot.class));
	}
	
	@Test
	public void leaveParking_WhenCarNotFound() {
		when(carRepo.findByPlateNumber(anyString())).thenReturn(null);
		ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
			tollService.leaveParking("FFFFFF");
		});
		
		assertEquals(thrown.getMessage(), "Car with plate number FFFFFF is not registered");
	}
	
	@Test
	public void leaveParking_WhenUsedSpotNotFound() {
		when(carRepo.findByPlateNumber(anyString())).thenReturn(car);
		when(spotRepo.findByCarId(anyLong())).thenReturn(null);
		ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
			tollService.leaveParking("FFFFFF");
		});
		
		assertEquals(thrown.getMessage(), "The spot was not set as occupied by plate number FFFFFF");
	}
	
	@Test
	public void leaveParking_WhenEntranceTimeNotRecorded() {
		when(carRepo.findByPlateNumber(anyString())).thenReturn(car);
		when(spotRepo.findByCarId(anyLong())).thenReturn(spot);
		spot.setEntranceTime(null);
		ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
			tollService.leaveParking("FFFFFF");
		});
		
		assertEquals(thrown.getMessage(), "The car entrance time was not recorded");
	}
}
