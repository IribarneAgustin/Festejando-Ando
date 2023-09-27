package com.microservice.festejandoando.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.microservice.festejandoando.model.Booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class HttpCallScheduler {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${ALLOWED_ORIGINS}")
    private String apiUrl;
    @Value("${APP_PRODUCTION_URL}")
    private String serverProductionUrl;

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
                System.out
                        .println("[" + timestamp.format(formatter) + "] HTTP Request to client was sent successfully.");
            } else {
                System.err.println("[" + timestamp.format(formatter)
                        + "] HTTP Request to client failed with status code: " + responseEntity.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("[" + timestamp.format(formatter) + "] HTTP Request to client failed with an exception: "
                    + e.getMessage());
        }
    }

    @Scheduled(fixedRate = 480000) // Scheduled to run every 8 minutes
    public void makeHttpRequestToServer() {
        String endpoint = serverProductionUrl + "/api/topic/list";

        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            List<Booking> response = restTemplate.exchange(
                    endpoint,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Booking>>() {
                    }).getBody();

            if (response != null) {
                System.out.println("[" + timestamp.format(formatter) + "] HTTP Request to server was sent successfully.");
            }
        } catch (Exception e) {
            System.err.println("[" + timestamp.format(formatter) + "] HTTP Request to server failed with an exception: "
                    + e.getMessage());
        }
    }
}
