package com.rent.backend.Repositories;

import com.rent.backend.Model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
}
