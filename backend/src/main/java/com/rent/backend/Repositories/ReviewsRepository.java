package com.rent.backend.Repositories;

import com.rent.backend.Model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findAllByProperty_Id(Long id);
    @Query(value = "SELECT AVG(r.review) FROM Reviews r WHERE r.property.id = :property_id")
    float getAvgPropertyReviews(Long property_id);
}
