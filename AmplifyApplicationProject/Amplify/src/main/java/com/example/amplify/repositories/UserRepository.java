package com.example.amplify.repositories;

import com.example.amplify.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);

    List<User> findByPassword(String password);

    List<User> findAllByUsername(String username, Pageable page);
}
