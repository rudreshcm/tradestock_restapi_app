package com.genpact.paypal.practice.tradestock.controller;

import com.genpact.paypal.practice.tradestock.dto.UserDTO;
import com.genpact.paypal.practice.tradestock.repository.UserRepository;
import com.genpact.paypal.practice.tradestock.response.APIResponse;
import com.genpact.paypal.practice.tradestock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/user")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO){

        return  userService.addUser(userDTO);
    }
}
