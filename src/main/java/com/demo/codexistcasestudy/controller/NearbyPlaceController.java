package com.demo.codexistcasestudy.controller;

import com.demo.codexistcasestudy.service.NearbyPlaceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NearbyPlaceController {

    private final NearbyPlaceService nearbyPlaceService;

    public NearbyPlaceController(NearbyPlaceService nearbyPlaceService) {
        this.nearbyPlaceService = nearbyPlaceService;
    }

    @GetMapping("/place")
    public ResponseEntity<String> getNearbyPlaces(@RequestParam Double longitude, @RequestParam Double latitude, @RequestParam Double radius) throws JsonProcessingException {
        String response = nearbyPlaceService.getNearbyPlaces(longitude, latitude, radius);
        return ResponseEntity.ok(response);
    }

}
