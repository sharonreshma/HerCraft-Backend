package com.example.demo;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServicee {

    @Autowired
    private UserRepo userRepo;
    

    public User registerUser(User user) {
        return userRepo.save(user);
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Get a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }
    public boolean deleteUserById(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
    public User updateUser(Long id, User user) {
        if (userRepo.existsById(id)) {
            user.setId(id); // Ensure the user ID is set to the existing ID
            return userRepo.save(user);
        }
        throw new RuntimeException("User not found");
    }
    
}