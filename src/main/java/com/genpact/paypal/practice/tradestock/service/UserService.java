package com.genpact.paypal.practice.tradestock.service;

import com.genpact.paypal.practice.tradestock.dto.UserDTO;
import com.genpact.paypal.practice.tradestock.exception.ConflictException;
import com.genpact.paypal.practice.tradestock.model.User;
import com.genpact.paypal.practice.tradestock.repository.UserRepository;
import com.genpact.paypal.practice.tradestock.response.APIResponse;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> addUser(UserDTO userDTO) {

        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new ConflictException("Email already exists");
        }

        User user = UserDTO.convertToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getFirstName().concat(result.getLastName())).toUri();

        return ResponseEntity.created(location).body(new APIResponse(true, "User registered successfully"));
    }
}
