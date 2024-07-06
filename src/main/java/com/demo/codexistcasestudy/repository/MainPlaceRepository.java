package com.demo.codexistcasestudy.repository;

import com.demo.codexistcasestudy.model.MainPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainPlaceRepository extends JpaRepository<MainPlace, Long> {

    Optional<MainPlace> findByLongitudeAndLatitudeAndRadius(Double longitude, Double latitude, Double radius);

}
