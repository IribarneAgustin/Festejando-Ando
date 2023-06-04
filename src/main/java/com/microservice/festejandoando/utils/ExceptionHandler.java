package com.microservice.festejandoando.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ExceptionHandler {

    @Autowired
    private static MessageSource messageSource;


    public static void internalServerErrorHandler(ResponseEntity<String> response){
        response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageSource.getMessage("internal.server.error", null, null));
    };
}
