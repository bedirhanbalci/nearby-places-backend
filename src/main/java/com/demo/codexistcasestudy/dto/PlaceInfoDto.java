package com.demo.codexistcasestudy.dto;

public record PlaceInfoDto(
        String place_id,
        Geometry geometry,
        String name,
        String vicinity
) {
}
