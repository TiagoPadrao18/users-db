package com.usersDb.usersDb;

import com.usersDb.usersDb.exception.AlreadyExistEmailException;
import com.usersDb.usersDb.exception.UserCreationException;
import com.usersDb.usersDb.model.User;
import com.usersDb.usersDb.repository.UserRepository;
import com.usersDb.usersDb.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void createUserShouldPass() {
        User user = new User();
        user.setName("tiago");
        user.setEmail("te@gmail.com");
        user.setPassword("testPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        Assertions.assertNotNull(userService.createUser(user));
    }


    @Test
    void createUserShouldUserCreationException() {
        User user = new User();
        user.setEmail("tiago@gmail.com");
        user.setPassword("password");
        Assertions.assertThrows(UserCreationException.class, () -> userService.createUser(user));
    }

    @Test
    void createUserWithAnExistEmailShouldBadRequest() {
        User user = new User();
        user.setName("tiago");
        user.setEmail("te@gmail.com");
        user.setPassword("testPassword");
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(Boolean.TRUE);
        Assertions.assertThrows(AlreadyExistEmailException.class, () -> userService.createUser(user));

    }



}
