package com.mine.idea.controller;

import com.mine.idea.api.UserApi;
import com.mine.idea.model.User;
import com.mine.idea.repository.UserRepository;
import com.mine.idea.security.JwtConfig;
import com.mine.idea.security.JwtTokenProvider;
import com.mine.idea.util.UserConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @stefanl
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserConverter userConverter;

    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/users")
    public ResponseEntity<String> signUp(@Valid @RequestBody User user) {

        if (StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getPassword())
                || StringUtils.isBlank(user.getName())) {
            return new ResponseEntity<>("Missing mandatory fields", null, HttpStatus.BAD_REQUEST);
        }
        User applicationUser = userRepository.findByEmail(user.getEmail());

        if (applicationUser == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            applicationUser = userRepository.save(user);
        }

        String response =
                "{\"jwt\":\"" + jwtTokenProvider.createToken(applicationUser.getEmail()) + "\"}";

        return new ResponseEntity<>(response, null, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<UserApi> getLoggedUser(HttpServletRequest req) {
        String header = req.getHeader(jwtConfig.getHeader());
        User applicationUser = userRepository.findByEmail(jwtTokenProvider.getUsername(header));

        if (applicationUser != null) {
            UserApi response = userConverter.toDTO(applicationUser);
            return new ResponseEntity<>(response, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
