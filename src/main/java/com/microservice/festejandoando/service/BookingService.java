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

import com.microservice.festejandoando.model.Booking;
import com.microservice.festejandoando.repository.IBookingRepository;
import com.microservice.festejandoando.utils.BookingValidatorImpl;
import com.microservice.festejandoando.utils.ExceptionHandler;

@Service
public class BookingService {

    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private BookingValidatorImpl validator;

    public List<Booking> findAll(){ 
        List<Booking> result = new ArrayList<>();
        try{
            result = bookingRepository.findByActiveTrue();
        }catch(Exception e){
            //TO DO logger
            e.printStackTrace();
        }
        return result;
    }

    public ResponseEntity<String> save(Booking booking){
        ResponseEntity<String> response = null;
        try{
            Errors result = new BeanPropertyBindingResult(booking, "booking");
            validator.validate(booking, result);
            if (!result.hasErrors()) {
                booking.setActive(Boolean.TRUE);
                bookingRepository.save(booking);
                response = ResponseEntity.ok(messageSource.getMessage("booking.saved.succesfully", null, null));
            } else {
                response = ExceptionHandler.handleErrors(result);
            }
        }catch(Exception e){
            //TO DO logger
            e.printStackTrace();
            ExceptionHandler.internalServerErrorHandler(response);
        }

        return response;
    }

    public ResponseEntity<String> update(Long id, Booking booking){
        ResponseEntity<String> response = validator.existsValidation(id);
        try {
            if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                Errors result = new BeanPropertyBindingResult(booking, "booking");
                validator.validate(booking, result);
                if (!result.hasErrors()) {
                    booking.setId(id);
                    booking.setActive(Boolean.TRUE);
                    bookingRepository.save(booking);
                    response = ResponseEntity.ok(messageSource.getMessage("booking.updated.succesfully", null, null));
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

    public ResponseEntity<String> logicalDeletion(Long id, Booking booking) {
        ResponseEntity<String> response = validator.existsValidation(id);
        try {
            if (!response.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                Errors result = new BeanPropertyBindingResult(booking, "booking");
                if (!result.hasErrors()) {
                    booking.setId(id);
                    booking.setActive(Boolean.FALSE);
                    bookingRepository.save(booking);
                    response = ResponseEntity.ok(messageSource.getMessage("booking.deleted.succesfully", null, null));
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
    
    
}
