package com.usersDb.usersDb.service;

import com.usersDb.usersDb.exception.*;
import com.usersDb.usersDb.model.User;
import com.usersDb.usersDb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        if (user.getName() == null || user.getEmail() == null|| user.getPassword() == null) throw new UserCreationException();
        if(this.userRepository.existsByEmail(user.getEmail())) throw new AlreadyExistEmailException("Already exist this email");
        return this.userRepository.save(user);

    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public User getUserById(final Long userId){
        if(!userRepository.existsById(userId)) throw new InvalidUserException();
        return this.userRepository.getReferenceById(userId);
    }

    public void deleteUserById(final Long userId) {
        if (!userRepository.existsById(userId)) throw new InvalidUserException();
        this.userRepository.deleteById(userId);
    }

    public List<User> findByName(String name) {
        List<User> users = this.userRepository.findByName(name);
        if (users.isEmpty()) throw new FindByNameException();
        return this.userRepository.findByName(name);
    }

    public User updateUserById(User user, final Long userId) {
        if(!userRepository.existsById(userId)) throw new InvalidUserException();
        if(user.getName()==null||user.getEmail()==null||user.getPassword()==null) throw new UserUpdateWithInvalidBodyException();
        if(this.userRepository.existsByEmail(user.getEmail())) throw new AlreadyExistEmailException("Can't change email.");
        User updatedUser = userRepository.getReferenceById(userId);
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }


    public User updateUserDetail(Long userId, User user) {
        if(!userRepository.existsById(userId)) throw new InvalidUserException();
        User updatedUser = userRepository.getReferenceById(userId);
        if (user.getName() != null) {
            updatedUser.setName(user.getName());
        }
        if (user.getPassword() != null) {
            updatedUser.setPassword(user.getPassword());
        }
        return userRepository.save(updatedUser);
    }
}
