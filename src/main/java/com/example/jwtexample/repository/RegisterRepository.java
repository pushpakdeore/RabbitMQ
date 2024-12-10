package com.example.jwtexample.repository;

import com.example.jwtexample.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<Register,Long> {
    boolean existsByUsername(String username);

    Optional<Register> findByUsernameAndPassword(String username, String password);
}
