package com.demo.codexistcasestudy.controller;

import com.demo.codexistcasestudy.dto.PlaceResponseDto;
import com.demo.codexistcasestudy.service.MainPlaceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainPlaceController {

    private final MainPlaceService mainPlaceService;

    public MainPlaceController(MainPlaceService mainPlaceService) {
        this.mainPlaceService = mainPlaceService;
    }

    @GetMapping("/place")
    public ResponseEntity<PlaceResponseDto> getNearbyPlaces(@RequestParam Double longitude, @RequestParam Double latitude, @RequestParam Double radius) throws JsonProcessingException {

        PlaceResponseDto response = mainPlaceService.getNearbyPlaces(longitude, latitude, radius);
        return ResponseEntity.ok(response);

    }

}
