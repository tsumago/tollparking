package com.amine.tollparking.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amine.tollparking.entity.Client;
import com.amine.tollparking.errorhandler.ApiException;
import com.amine.tollparking.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {
	@Mock
	private ClientRepository clientRepo;
	
	@InjectMocks
	private ClientService clientService;
	
	private Client client;
	
	@BeforeEach
	public void resetClient() {
		client = new Client("Amine", "Boulifa", "Aminus", 100);
	}
	
	@Test
	public void clientEdition_WhenNewClientIsNull() {
		ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
			clientService.editClient(client, null);
		    });
		
		assertEquals(thrown.getMessage(), "Error while editing existing client");
	}
	
	@Test
	public void clientEdition_WhenOldClientIsNull() {
		ApiException thrown = Assertions.assertThrows(ApiException.class, () -> {
			clientService.editClient(null, client);
		    });
		
		assertEquals(thrown.getMessage(), "Error while editing existing client");
	}
	
	@Test
	public void clientEdition_WhenFieldIsMissing() {
		Client newClient = new Client("Joe", "Derson", null, 100);
		when(clientRepo.save(any(Client.class))).thenReturn(null);
		
		clientService.editClient(client, newClient);
		
		verify(clientRepo, times(1)).save(any(Client.class));
	}
	
	@Test
	public void clientEdition_WhenHappyPath() {
		Client newClient = new Client("Joe", "Derson", "DEwi", 100);
		when(clientRepo.save(any(Client.class))).thenReturn(null);
		
		clientService.editClient(client, newClient);
		
		verify(clientRepo, times(1)).save(any(Client.class));
	}
}
