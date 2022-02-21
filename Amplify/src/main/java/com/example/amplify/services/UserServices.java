package com.example.amplify.services;

import com.example.amplify.model.User;
import com.example.amplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepo;

    public List<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> findByPassword(String password) {
        return userRepo.findByPassword(password);
    }
    public List<User> findAll(){return userRepo.findAll();}

}
