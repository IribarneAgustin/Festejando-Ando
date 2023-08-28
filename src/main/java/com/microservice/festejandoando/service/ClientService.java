package com.microservice.festejandoando.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.microservice.festejandoando.model.Client;
import com.microservice.festejandoando.repository.IClientRepository;
import com.microservice.festejandoando.utils.ClientValidatorImpl;
import com.microservice.festejandoando.utils.ExceptionHandler;

@Service
public class ClientService {

	@Autowired
	private IClientRepository clientRepository;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ClientValidatorImpl validator;

	public List<Client> findAll() {
		List<Client> result = new ArrayList<>();
		try {
			result = clientRepository.findByActiveTrue();
		} catch (Exception e) {
			// TO DO logger
			e.printStackTrace();
		}
		return result;
	}

	public ResponseEntity<String> save(Client client) {
		ResponseEntity<String> response = null;
		try {
			Errors result = new BeanPropertyBindingResult(client, "client");
			validator.validate(client, result);
			if (!result.hasErrors()) {
				client.setActive(Boolean.TRUE);
				Client newCLient = clientRepository.save(client);
				response = ResponseEntity.ok(messageSource.getMessage("client.saved.succesfully", null, null) + " ID: " + newCLient.getId());
			} else {
				response = ExceptionHandler.handleErrors(result);
			}
		} catch (Exception e) {
			// TO DO logger
			e.printStackTrace();
			ExceptionHandler.internalServerErrorHandler(response);
		}

		return response;
	}

	public ResponseEntity<String> update(Long id, Client client) {
		ResponseEntity<String> response = validator.existsValidation(id);
		try {
			if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				Errors result = new BeanPropertyBindingResult(client, "client");
				validator.validate(client, result);
				if (!result.hasErrors()) {
					client.setId(id);
					client.setActive(Boolean.TRUE);
					clientRepository.save(client);
					response = ResponseEntity.ok(messageSource.getMessage("client.updated.succesfully", null, null));
				} else {
					response = ExceptionHandler.handleErrors(result);
				}
			}
		} catch (Exception e) {
			// TO DO logger
			e.printStackTrace();
			ExceptionHandler.internalServerErrorHandler(response);
		}

		return response;
	}

	public ResponseEntity<String> logicalDeletion(Long id, Client client) {
		ResponseEntity<String> response = validator.existsValidation(id);
		try {
			if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				Errors result = new BeanPropertyBindingResult(client, "client");
				if (!result.hasErrors()) {
					client.setId(id);
					client.setActive(Boolean.FALSE);
					clientRepository.save(client);
					response = ResponseEntity.ok(messageSource.getMessage("client.deleted.succesfully", null, null));
				} else {
					response = ExceptionHandler.handleErrors(result);
				}
			}
		} catch (Exception e) {
			// TO DO logger
			e.printStackTrace();
			ExceptionHandler.internalServerErrorHandler(response);
		}

		return response;
	}

	public ResponseEntity<?> findByEmail(String email){
		Client client = clientRepository.findByEmail(email);
		ResponseEntity<?> response;

        if (client != null) {
            response = ResponseEntity.ok(client); // Return 200 OK with client data
        } else {
            response = ResponseEntity.notFound().build(); // Return 404 Not Found
        }

		return response;
	}
}
