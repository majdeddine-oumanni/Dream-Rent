package com.rent.backend.Repositories;

import com.rent.backend.Model.Role;
import com.rent.backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    long count();

    @Query(value = "SELECT count(r) FROM User r WHERE r.role = :role")
    long getNumberOfUsersByRole(Role role);
}
