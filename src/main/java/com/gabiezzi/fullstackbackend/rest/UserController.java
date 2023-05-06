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

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){ return  userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));}

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User userUpdate , @PathVariable  Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userUpdate.getUsername());
                    user.setName(userUpdate.getName());
                    user.setEmail(userUpdate.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if (!userRepository.existsById(id))
            throw new UserNotFoundException(id);

        userRepository.deleteById(id);
        return "User with id "+ id + " has been deleted successfully.";
    }
}
