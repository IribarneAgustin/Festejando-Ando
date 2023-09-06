package com.microservice.festejandoando.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.festejandoando.model.Booking;
import com.microservice.festejandoando.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {


    @Autowired
    private BookingService bookingService;


    @GetMapping("/list")
    public List<Booking> findActive(){
        return bookingService.findActive();
    }
    
    @GetMapping("/listHistory")
    public List<Booking> findAllHistory(){
        return bookingService.findAllHistory();
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Booking booking){
        return bookingService.save(booking);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Booking booking) {
        return bookingService.update(id, booking);
    }

    @PatchMapping("/confirm/{id}")
    public ResponseEntity<String> confirm(@PathVariable Long id, @RequestBody Boolean confirm) {
        return bookingService.updateConfirm(id, confirm);
    }
    
    @PatchMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id, @RequestBody Boolean confirm) {
        return bookingService.updateToCancel(id, confirm);
    }
    
    @PutMapping("/delete/{id}")
    public ResponseEntity<String> logicalDeletion(@PathVariable Long id, @RequestBody Booking booking ) {
        return bookingService.logicalDeletion(id, booking);
    }  
}
