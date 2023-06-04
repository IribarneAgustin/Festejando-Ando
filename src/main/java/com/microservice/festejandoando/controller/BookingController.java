package com.microservice.festejandoando.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.festejandoando.model.Booking;
import com.microservice.festejandoando.service.BookingService;

@RestController
@RequestMapping("booking")
public class BookingController {


    @Autowired
    private BookingService bookingService;


    @GetMapping("/list")
    public List<Booking> findAll(){
        return bookingService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Booking booking){
        return bookingService.save(booking);
        
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Booking booking ) {
        return bookingService.update(id, booking);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> logicalDeletion(@PathVariable Long id, @RequestBody Booking booking ) {
        return bookingService.logicalDeletion(id, booking);
    }


    
}
