package com.mine.idea.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mine.idea.model.User;
import com.mine.idea.repository.UserRepository;
import com.mine.idea.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @stefanl
 */
@RestController
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/users")
    public ResponseEntity<String> signUp(@RequestBody User user) throws JsonProcessingException {
        User applicationUser = userRepository.findByEmail(user.getEmail());

        if (applicationUser == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            applicationUser = userRepository.save(user);
        }

        String response =
                "{\"jwt\":\"" + jwtTokenProvider.generateToken(applicationUser.getEmail()) + "\"}";

        return new ResponseEntity<>(response,null,HttpStatus.CREATED);
    }
}
