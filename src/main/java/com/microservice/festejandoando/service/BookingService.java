package com.microservice.festejandoando.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.festejandoando.model.Booking;
import com.microservice.festejandoando.repository.IBookingRepository;
import com.microservice.festejandoando.utils.ExceptionHandler;

@Service
public class BookingService {

    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private MessageSource messageSource;

    public List<Booking> findAll(){
        List<Booking> result = new ArrayList<>();
        try{
            result = bookingRepository.findAll();
        }catch(Exception e){
            //TO DO logger
            e.printStackTrace();
        }
        return result;
    }

    public ResponseEntity<String> save(Booking booking){
        ResponseEntity<String> response = null;
        try{
            booking.setActive(Boolean.TRUE);
            bookingRepository.save(booking);
            response = ResponseEntity.ok(messageSource.getMessage("booking.saved.succesfully", null, null));
        }catch(Exception e){
            //TO DO logger
            ExceptionHandler.internalServerErrorHandler(response);
        }

        return response;
    }

    public ResponseEntity<String> update(Long id, Booking booking){
        ResponseEntity<String> response = null;
        try {
            Optional<Booking> existingBooking = bookingRepository.findById(id);
            if (existingBooking.isPresent()) {
                booking.setId(id);
                bookingRepository.save(booking);
                response = ResponseEntity.ok(messageSource.getMessage("booking.updated.succesfully", null, null));
            } else {
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageSource.getMessage("booking.updated.error", null, null));
            }
        } catch (Exception e) {
            //TO DO logger
            ExceptionHandler.internalServerErrorHandler(response);
        }

		return response;
	}

	public ResponseEntity<String> logicalDeletion(Long id, Booking booking) {

		ResponseEntity<String> response = null;
		try {
			Optional<Booking> existingBooking = bookingRepository.findById(id);
			if (existingBooking.isPresent()) {
				booking.setId(id);
				booking.setActive(Boolean.FALSE);
				bookingRepository.save(booking);
				response = ResponseEntity.ok(messageSource.getMessage("booking.deleted.succesfully", null, null));
			} else {
				response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(messageSource.getMessage("booking.deleted.error", null, null));
			}
		} catch (Exception e) {
			// TO DO logger
			ExceptionHandler.internalServerErrorHandler(response);
		}

		return response;
	}
    
}
