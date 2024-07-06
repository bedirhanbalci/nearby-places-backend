package com.demo.codexistcasestudy.service;

import com.demo.codexistcasestudy.dto.PlacesInfoDto;
import com.demo.codexistcasestudy.model.NearbyPlace;
import com.demo.codexistcasestudy.repository.NearbyPlaceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class NearbyPlaceService {

    private final NearbyPlaceRepository nearbyPlaceRepository;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public NearbyPlaceService(NearbyPlaceRepository nearbyPlaceRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.nearbyPlaceRepository = nearbyPlaceRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.url}")
    private String apiUrl;

    public String getNearbyPlaces(Double longitude, Double latitude, Double radius) throws JsonProcessingException {
        Optional<NearbyPlace> existingNearbyPlace = nearbyPlaceRepository.findByLongitudeAndLatitudeAndRadius(longitude, latitude, radius);
        if (existingNearbyPlace.isPresent()) {
            return existingNearbyPlace.get().getJsonResponse();
        } else {
            String url = String.format(apiUrl, latitude, longitude, radius, apiKey);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            PlacesInfoDto placesInfoDto = objectMapper.readValue(response.getBody(), PlacesInfoDto.class);

            NearbyPlace nearbyPlace = new NearbyPlace();
            nearbyPlace.setLongitude(longitude);
            nearbyPlace.setLatitude(latitude);
            nearbyPlace.setRadius(radius);
//            nearbyPlace.setJsonResponse(response);
            nearbyPlaceRepository.save(nearbyPlace);

            return null;
        }
    }
}
