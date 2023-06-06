package com.microservice.festejandoando.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservice.festejandoando.model.Booking;

@Repository
public interface IBookingRepository extends JpaRepository<Booking, Long>{

    List<Booking> findByActiveTrue();
    Boolean existsByIdAndActiveTrue(Long id);
}
