package com.usersDb.usersDb.service;

import com.usersDb.usersDb.exception.AlreadyExistEmailException;
import com.usersDb.usersDb.exception.FindByNameException;
import com.usersDb.usersDb.exception.InvalidUserException;
import com.usersDb.usersDb.exception.UserCreationException;
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
        if (user.getName().equals("") || user.getEmail().equals("") || user.getPassword().equals("")) throw new UserCreationException();
        if(this.userRepository.existsByEmail(user.getEmail())) throw new AlreadyExistEmailException("Already exist this email");
        return this.userRepository.save(user);

    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
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
        User updatedUser = userRepository.getReferenceById(userId);
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }


    public User updateUserDetail(Long userId, User user) {
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
