package com.demo.codexistcasestudy.repository;

import com.demo.codexistcasestudy.model.NearbyPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NearbyPlaceRepository extends JpaRepository<NearbyPlace, Long> {

    Optional<NearbyPlace> findByLongitudeAndLatitudeAndRadius(Double longitude, Double latitude, Double radius);

}
