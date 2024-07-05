package com.demo.codexistcasestudy.service;

import com.demo.codexistcasestudy.model.NearbyPlace;
import com.demo.codexistcasestudy.repository.NearbyPlaceRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class NearbyPlaceService {

    private final NearbyPlaceRepository nearbyPlaceRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public NearbyPlaceService(NearbyPlaceRepository nearbyPlaceRepository) {
        this.nearbyPlaceRepository = nearbyPlaceRepository;
    }

    @Value("${google.api.key}")
    private String apiKey;

    public String getNearbyPlaces(Double longitude, Double latitude, Double radius) {
        Optional<NearbyPlace> existingNearbyPlace = nearbyPlaceRepository.findByLongitudeAndLatitudeAndRadius(longitude, latitude, radius);
        if (existingNearbyPlace.isPresent()) {
            return existingNearbyPlace.get().getJsonResponse();
        } else {
            String url = String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%f&key=%s", longitude, latitude, radius, apiKey);

            String response = restTemplate.getForObject(url, String.class);

            NearbyPlace nearbyPlace = new NearbyPlace();
            nearbyPlace.setLongitude(longitude);
            nearbyPlace.setLatitude(latitude);
            nearbyPlace.setRadius(radius);
            nearbyPlace.setJsonResponse(response);
            nearbyPlaceRepository.save(nearbyPlace);

            return response;
        }
    }
}
