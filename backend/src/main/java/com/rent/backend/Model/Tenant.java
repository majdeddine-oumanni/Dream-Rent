package com.rent.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Tenant extends User{
    @OneToMany(mappedBy = "tenant")
    private List<Reservation> reservations;
}
