package com.microservice.festejandoando.utils;
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
import com.microservice.festejandoando.repository.IClientRepository;

@Component
public class BookingValidatorImpl implements Validator{

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private IClientRepository clientRepository;
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

            if (booking.getClient().getEmail() == null) {
                errors.rejectValue("client", messageSource.getMessage("field.required.error", new Object[] { "email" }, null));

            } else {

                Client client = clientRepository.findByEmail(booking.getClient().getEmail());
                if (booking.getClient().getEmail() != null && client != null) {
                    booking.setClient(client);
                }
                // if not exists, there is an annotation on Booking entity with the aim of save it
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
