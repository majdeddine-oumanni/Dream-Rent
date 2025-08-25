package com.rent.backend.Repositories;

import com.rent.backend.Model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findAllByProperty_Id(Long id);
}
