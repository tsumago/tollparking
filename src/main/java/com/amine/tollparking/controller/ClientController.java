package com.amine.tollparking.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amine.tollparking.entity.Client;
import com.amine.tollparking.repository.ClientRepository;
import com.amine.tollparking.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ClientService clientService;
	
	private final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Client createUser(@RequestBody @Valid Client newClient) {
		logger.info("Creating new user {}", newClient.getUsername());
		return clientService.createClient(newClient);
	}
	
	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.FOUND)
	public Client getClient(@PathVariable String username) {
		logger.info("Fetching user {}", username);
		return clientRepo.findByUsername(username);
	}
	
	@PutMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Client editClient(@RequestBody @Valid Client client) {
		logger.info("Editing user {}", client.getUsername());
		Client old = clientRepo.findByUsername(client.getUsername());
		
		return clientService.editClient(old, client);
	}
	
	@DeleteMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public long deleteClient(@PathVariable String username) {
		logger.info("Deleting user {}", username);
		return clientRepo.deleteByUsername(username);
	}
}
