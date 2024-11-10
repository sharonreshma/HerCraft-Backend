package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRegister authenticateUser(String email, String password) {
        Optional<UserRegister> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            UserRegister user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } 
        }
        throw new IllegalArgumentException("Invalid email or password");
    }
    public List<UserRegister> getAllUsers() {
        return userRepository.findAll();
    }
}
