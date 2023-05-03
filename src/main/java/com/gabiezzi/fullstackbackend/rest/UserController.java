package com.gabiezzi.fullstackbackend.rest;

import com.gabiezzi.fullstackbackend.exception.UserNotFoundException;
import com.gabiezzi.fullstackbackend.model.User;
import com.gabiezzi.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") Long id){ return  userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));}
}
