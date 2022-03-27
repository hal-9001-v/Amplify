package com.example.amplify.repositories;

import com.example.amplify.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findAllByUsername(String username, Pageable page);

    List<User> findByEmail(String email);

}
