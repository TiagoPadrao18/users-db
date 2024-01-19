package com.usersDb.usersDb.service;

import com.usersDb.usersDb.model.User;
import com.usersDb.usersDb.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser (@RequestBody User user) {
        return this.userRepository.save(user);

    }
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public void  deleteUserById(@PathVariable final Long userId){
        this.userRepository.deleteById(userId);
    }

}
