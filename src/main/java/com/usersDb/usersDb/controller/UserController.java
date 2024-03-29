package com.usersDb.usersDb.controller;

import com.usersDb.usersDb.model.User;
import com.usersDb.usersDb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable final Long userId){
        return userService.getUserById(userId);
    }


    @GetMapping(params = "name")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByName(@RequestParam(required = false) String name){
        return userService.findByName(name);
    }


    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable final Long userId){
        userService.deleteUserById(userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUserById(@RequestBody User user, @PathVariable final Long userId){
       return userService.updateUserById(user,userId);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User user, @PathVariable final Long userId){
        return userService.updateUserDetail(userId,user);
    }


}
