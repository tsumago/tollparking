package com.amine.tollparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amine.tollparking.entity.CarType;
import com.amine.tollparking.entity.Spot;

public interface SpotRepository extends JpaRepository<Spot, Long> {
	Spot findFirstBySpotTypeAndParkingIdAndCarIdIsNull(CarType type, Long parkingId);
	Spot findByCarId(Long carId);
}
