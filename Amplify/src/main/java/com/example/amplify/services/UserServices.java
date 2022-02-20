package com.example.amplify.services;

import com.example.amplify.model.User;
import com.example.amplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepo;

    public List<User> findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public List<User> findByPassword(String password) {

        return userRepo.findByPassword(password);

    }

}
