package com.rent.backend.Repositories;

import com.rent.backend.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByProperty_Id(Long id);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.property.id = :property_id")
    Long getReservationsNumberOfProperty(Long property_id);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.property.owner.id = :owner_id")
    Long getReservationsNumberByOwnerId(Long owner_id);
}
