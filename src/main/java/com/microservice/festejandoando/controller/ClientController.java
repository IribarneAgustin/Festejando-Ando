package com.microservice.festejandoando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.festejandoando.model.Client;
import com.microservice.festejandoando.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GetMapping("/list")
	public List<Client> findAll() {
		return clientService.findAll();
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Client client) {
		return clientService.save(client);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Client client) {
		return clientService.update(id, client);
	}

	@PutMapping("/delete/{id}")
	public ResponseEntity<String> logicalDeletion(@PathVariable Long id, @RequestBody Client client) {
		return clientService.logicalDeletion(id, client);
	}

	@GetMapping("/findByEmail/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {
		return clientService.findByEmail(email);
	}

}
