package com.microservice.festejandoando.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

public abstract class ExceptionHandler {

	@Autowired
	private static MessageSource messageSource;

	public static void internalServerErrorHandler(ResponseEntity<String> response) {
		response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(messageSource.getMessage("internal.server.error", null, null));
	};

	public static ResponseEntity<String> handleErrors(Errors result) {
		List<String> errorCodes = new ArrayList<>();
		result.getAllErrors().forEach(error -> {
			String errorCode = error.getCode();
			errorCodes.add(errorCode);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCodes.toString());
	}
}
