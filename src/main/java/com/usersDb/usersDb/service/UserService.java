package com.usersDb.usersDb.service;

import com.usersDb.usersDb.model.User;
import com.usersDb.usersDb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        return this.userRepository.save(user);

    }
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void deleteUserById(final Long userId) {
        this.userRepository.deleteById(userId);
    }

    public List<User> findByName(String name) {
        return this.userRepository.findByName(name);
    }

    public User updateUserById( User user, final Long userId){
       User updatedUser = userRepository.getReferenceById(userId);
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }



}
