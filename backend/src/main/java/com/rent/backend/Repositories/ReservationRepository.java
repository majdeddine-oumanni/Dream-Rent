package com.rent.backend.Repositories;

import com.rent.backend.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByProperty_Id(Long id);
}
