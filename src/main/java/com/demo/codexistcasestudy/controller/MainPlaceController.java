package com.demo.codexistcasestudy.controller;

import com.demo.codexistcasestudy.dto.PlaceResponseDto;
import com.demo.codexistcasestudy.service.MainPlaceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Google Map API V1", description = "Google Map API to filter current latitude, longitude, radius by nearby places")
public class MainPlaceController {

    private final MainPlaceService mainPlaceService;

    public MainPlaceController(MainPlaceService mainPlaceService) {
        this.mainPlaceService = mainPlaceService;
    }

    @Operation(
            method = "GET",
            summary = "Google Map API V1",
            description = "Google Map API to filter current latitude, longitude, radius by nearby places",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PlaceResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Bad Request",
                            content = @Content(
                                    schema = @Schema(hidden = true)
                            )
                    )
            }
    )
    @GetMapping("/place")
    public ResponseEntity<PlaceResponseDto> getNearbyPlaces(@RequestParam Double longitude, @RequestParam Double latitude, @RequestParam Double radius) throws JsonProcessingException {

        PlaceResponseDto response = mainPlaceService.getNearbyPlaces(longitude, latitude, radius);
        return ResponseEntity.ok(response);

    }

}
