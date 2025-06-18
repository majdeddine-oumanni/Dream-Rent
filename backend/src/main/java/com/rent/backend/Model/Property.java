package com.rent.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private int roomsNumber;
    private boolean availability;
    private double price;
    @ElementCollection
    private List<String> images = new ArrayList<>() ;

    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @ManyToOne
    private Owner owner;

    @OneToMany(mappedBy = "property")
    private List<Reservation> reservations;

}
