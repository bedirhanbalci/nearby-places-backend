package com.demo.codexistcasestudy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "main_places")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "radius")
    private Double radius;

    @Column(name = "place_id")
    private String placeId;

    @Column(name = "vicinity")
    private String vicinity;

    @OneToMany(mappedBy = "mainPlace", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<NearbyPlace> nearbyPlaceList;

}
