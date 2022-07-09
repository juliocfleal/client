package com.julioleal.client.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julioleal.client.entities.Client;

@RestController
@RequestMapping(value = "/clients")
public class ClientsResource {

	
	@GetMapping
	public ResponseEntity<Client> getAllClients(){
		
	}
}
