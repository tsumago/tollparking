package com.amine.tollparking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.amine.tollparking.entity.Car;
import com.amine.tollparking.entity.Client;
import com.amine.tollparking.errorhandler.ApiException;
import com.amine.tollparking.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepo;

	public Client editClient(Client oldClient, Client newClient) {		
		try {
			oldClient.setBalance(newClient.getBalance());
			oldClient.setFirstName(newClient.getFirstName());
			oldClient.setLastName(newClient.getLastName());
		} catch (Exception e) {
			throw new ApiException("Error while editing existing client", HttpStatus.UNPROCESSABLE_ENTITY, e);
		}

		return clientRepo.save(oldClient);
	}

	public Client createClient(Client newClient) {
		try {
			for (Car car : newClient.getCars()) {
				car.setClient(newClient);
			}
		} catch (Exception e) {
			throw new ApiException("Error while creating new client", HttpStatus.FAILED_DEPENDENCY, e);
		}

		return clientRepo.save(newClient);
	}
}
