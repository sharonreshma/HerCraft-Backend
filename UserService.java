package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserRegister registerUser(String username, String email, String password) {
        // Check if user already exists with the same email
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User with email already exists");
        }

        // Create new user
        UserRegister user = new UserRegister();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

       

        // Save user to the database
        return userRepository.save(user);
    }

    public List<UserRegister> getAllUsers() {
        return userRepository.findAll();
    }
    public void deleteUserById(Long id) {
        Optional<UserRegister> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
    
}
