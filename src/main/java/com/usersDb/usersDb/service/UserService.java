package com.usersDb.usersDb.service;

import com.usersDb.usersDb.exception.UserCreationException;
import com.usersDb.usersDb.model.User;
import com.usersDb.usersDb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
    if (user.getName().equals("")) throw  new UserCreationException();
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

    public User updateUserById(User user, final Long userId) {
        User updatedUser = userRepository.getReferenceById(userId);
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }

}
