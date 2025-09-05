package com.rent.backend.Repositories;

import com.rent.backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "SELECT COUNT(r) FROM User r")
    long getAllUsersNumber();
}
