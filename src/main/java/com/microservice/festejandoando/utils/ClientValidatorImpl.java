package com.microservice.festejandoando.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.microservice.festejandoando.model.Client;
import com.microservice.festejandoando.repository.IClientRepository;

@Component
public class ClientValidatorImpl implements Validator {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private IClientRepository clientRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return Client.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				messageSource.getMessage("field.required.error", new Object[] { "name" }, null));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				messageSource.getMessage("field.required.error", new Object[] { "email" }, null));
	}

	public ResponseEntity<String> existsValidation(Long id) {
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK).body(null);
		if (!clientRepository.existsByIdAndActiveTrue(id)) {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(messageSource.getMessage("client.deleted.error", null, null));
		}
		return response;
	}
}
