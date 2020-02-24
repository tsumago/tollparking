package com.amine.tollparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amine.tollparking.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	public Client findByUsername(String username);
	
	public int deleteByUsername(String username);
}
