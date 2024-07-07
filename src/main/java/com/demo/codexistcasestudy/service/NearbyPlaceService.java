package com.demo.codexistcasestudy.service;

import com.demo.codexistcasestudy.model.NearbyPlace;
import com.demo.codexistcasestudy.repository.NearbyPlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class NearbyPlaceService {

    private final NearbyPlaceRepository nearbyPlaceRepository;

    public NearbyPlaceService(NearbyPlaceRepository nearbyPlaceRepository) {
        this.nearbyPlaceRepository = nearbyPlaceRepository;
    }

    public void save(NearbyPlace nearbyPlace) {
        this.nearbyPlaceRepository.save(nearbyPlace);
    }

}
