package com.demo.codexistcasestudy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "nearby_places")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NearbyPlace {

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

    @ManyToOne
    @JoinColumn(name = "main_place_id")
    @JsonIgnore
    private MainPlace mainPlace;

}
