package com.usersDb.usersDb.controller;

import com.usersDb.usersDb.model.User;
import com.usersDb.usersDb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;



    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser (@RequestBody User user) {
        return this.userRepository.save(user);

    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.FOUND)
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

}
