package com.demo.codexistcasestudy.dto;

import com.demo.codexistcasestudy.model.NearbyPlace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceResponseDto {

    private String place_id;

    private String name;

    private String vicinity;

    private List<NearbyPlace> nearbyPlaceList;

}
