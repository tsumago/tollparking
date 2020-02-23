package com.amine.tollparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amine.tollparking.entity.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {}
