package com.microservice.festejandoando.utils;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.microservice.festejandoando.model.Booking;
import com.microservice.festejandoando.model.Client;
import com.microservice.festejandoando.repository.IBookingRepository;
import com.microservice.festejandoando.service.ClientService;

@Component
public class BookingValidatorImpl implements Validator{

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ClientService clientService;
    @Autowired
    private IBookingRepository bookingRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Booking.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Booking booking = (Booking) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "client", messageSource.getMessage("field.required.error", new Object[]{"client"}, null));
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", messageSource.getMessage("field.required.error", new Object[]{"date"}, null));
        clientValidation(booking, errors);
    }

    private void clientValidation(Booking booking, Errors errors) {
        if (booking.getClient() != null) {
            Long clientId = booking.getClient().getId();
            Optional<Client> client = clientService.findById(clientId);
            if (!client.isPresent()) {
                errors.rejectValue("client", messageSource.getMessage("client.notExists.error", new Object[] { "client" }, null));
            }
        }
    }

    public ResponseEntity<String> existsValidation(Long id){
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK).body(null);
        if(!bookingRepository.existsByIdAndActiveTrue(id)){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage("booking.deleted.error", null, null));
        }
        return response;
    }



    
}
