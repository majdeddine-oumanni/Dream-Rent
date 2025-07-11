package com.rent.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Owner extends User{
    @OneToMany(mappedBy = "owner")
    private List<Property> properties;
}
