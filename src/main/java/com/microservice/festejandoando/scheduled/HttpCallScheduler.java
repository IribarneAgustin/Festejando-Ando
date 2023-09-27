package com.microservice.festejandoando.scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class HttpCallScheduler {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${ALLOWED_ORIGINS}")
    private String apiUrl;


    @Scheduled(fixedRate = 480000) // Scheduled to run every 8 minutes 
    public void makeHttpRequest() {
        String endpoint = apiUrl;

        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            ResponseEntity<Void> responseEntity = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null, // Request body (none in this case)
                Void.class // Response type (no response body)
            );

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                System.out.println("[" + timestamp.format(formatter) + "] HTTP Request was sent successfully.");
            } else {
                System.err.println("[" + timestamp.format(formatter) + "] HTTP Request failed with status code: " + responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("[" + timestamp.format(formatter) + "] HTTP Request failed with an exception: " + e.getMessage());
        }
    }
}
