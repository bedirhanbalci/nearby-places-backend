package com.demo.codexistcasestudy.service;

import com.demo.codexistcasestudy.dto.PlaceResponseDto;
import com.demo.codexistcasestudy.dto.PlacesInfoDto;
import com.demo.codexistcasestudy.model.MainPlace;
import com.demo.codexistcasestudy.model.NearbyPlace;
import com.demo.codexistcasestudy.repository.MainPlaceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MainPlaceService {

    private final MainPlaceRepository mainPlaceRepository;

    private final NearbyPlaceService nearbyPlaceService;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public MainPlaceService(MainPlaceRepository mainPlaceRepository, NearbyPlaceService nearbyPlaceService, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.mainPlaceRepository = mainPlaceRepository;
        this.nearbyPlaceService = nearbyPlaceService;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${google.api.url}")
    private String apiUrl;

    @Transactional
    public PlaceResponseDto getNearbyPlaces(Double longitude, Double latitude, Double radius) throws JsonProcessingException {

        Optional<MainPlace> existingMainPlace = mainPlaceRepository.findByLongitudeAndLatitudeAndRadius(longitude, latitude, radius);
        if (existingMainPlace.isPresent()) {

            PlaceResponseDto placeResponseDto = new PlaceResponseDto();
            placeResponseDto.setPlace_id(existingMainPlace.get().getPlaceId());
            placeResponseDto.setVicinity(existingMainPlace.get().getVicinity());
            placeResponseDto.setName(existingMainPlace.get().getName());
            placeResponseDto.setNearbyPlaceList(existingMainPlace.get().getNearbyPlaceList());
            return placeResponseDto;

        } else {
            String url = String.format(apiUrl, latitude, longitude, radius, apiKey);

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            PlacesInfoDto placesInfoDto = objectMapper.readValue(response.getBody(), PlacesInfoDto.class);

            MainPlace mainPlace = new MainPlace();
            mainPlace.setName(placesInfoDto.results().getFirst().name());
            mainPlace.setPlaceId(placesInfoDto.results().getFirst().place_id());
            mainPlace.setVicinity(placesInfoDto.results().getFirst().vicinity());
            mainPlace.setLongitude(longitude);
            mainPlace.setLatitude(latitude);
            mainPlace.setRadius(radius);

            MainPlace savedMainPlace = mainPlaceRepository.save(mainPlace);
            mainPlaceRepository.flush();

            List<NearbyPlace> nearbyPlaceList = new ArrayList<>();
            placesInfoDto.results().forEach(placeInfoDto -> {
                NearbyPlace nearbyPlace = new NearbyPlace();
                nearbyPlace.setMainPlace(savedMainPlace);
                nearbyPlace.setPlaceId(placeInfoDto.place_id());
                nearbyPlace.setVicinity(placeInfoDto.vicinity());
                nearbyPlace.setName(placeInfoDto.name());
                nearbyPlace.setLatitude(placeInfoDto.geometry().location().lat());
                nearbyPlace.setLongitude(placeInfoDto.geometry().location().lng());
                nearbyPlaceList.add(nearbyPlace);
                nearbyPlaceService.save(nearbyPlace);
            });

            savedMainPlace.setNearbyPlaceList(nearbyPlaceList);

            this.mainPlaceRepository.save(savedMainPlace);

            PlaceResponseDto placeResponseDto = new PlaceResponseDto();
            placeResponseDto.setPlace_id(savedMainPlace.getPlaceId());
            placeResponseDto.setVicinity(savedMainPlace.getVicinity());
            placeResponseDto.setName(savedMainPlace.getName());
            placeResponseDto.setNearbyPlaceList(nearbyPlaceList);
            return placeResponseDto;
        }

    }
}
