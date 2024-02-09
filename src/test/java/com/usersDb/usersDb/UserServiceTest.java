package com.usersDb.usersDb;

import com.usersDb.usersDb.exception.AlreadyExistEmailException;
import com.usersDb.usersDb.exception.InvalidUserException;
import com.usersDb.usersDb.exception.UserCreationException;
import com.usersDb.usersDb.exception.UserUpdateWithInvalidBodyException;
import com.usersDb.usersDb.model.User;
import com.usersDb.usersDb.repository.UserRepository;
import com.usersDb.usersDb.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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


    @Test
    void findByIdShouldAccess() {
        User user = new User();
        user.setId(10L);
        user.setName("asdasda");
        user.setEmail("aaa@gmail.com");
        user.setPassword("test123");
        when(userRepository.existsById(user.getId())).thenReturn(Boolean.TRUE);
        when(userRepository.getReferenceById(user.getId())).thenReturn(user);
        Assertions.assertEquals(user, userService.getUserById(user.getId()));
    }


    @Test
    void updateUserShouldSuccess() {
        User user = new User();
        user.setId(1L);
        user.setName("TEST");
        user.setEmail("test@gmail.com");
        user.setPassword("password");

        user.setName("test");
        user.setPassword("new");

        when(userRepository.existsById(user.getId())).thenReturn(Boolean.TRUE);
        when(userRepository.getReferenceById(user.getId())).thenReturn(user);

        Assertions.assertEquals(user, userService.getUserById(user.getId()));

    }


    @Test
    void updateUserDetailShouldSuccess() {
        User user = new User();
        user.setId(1L);
        user.setName("TEST");
        user.setEmail("test@gmail.com");
        user.setPassword("password");

        user.setName("test");

        when(userRepository.existsById(user.getId())).thenReturn(Boolean.TRUE);
        when(userRepository.getReferenceById(user.getId())).thenReturn(user);

        Assertions.assertEquals(user, userService.getUserById(user.getId()));

    }


    @Test
    void updateUserShouldNotFound() {
        User user = new User();
        user.setId(1L);
        user.setName("TEST");
        user.setEmail("test@gmail.com");
        user.setPassword("password");

        user.setName("test");

        when(userRepository.existsById(user.getId())).thenReturn(Boolean.FALSE);
        when(userRepository.getReferenceById(user.getId())).thenReturn(user);

        Assertions.assertThrows(InvalidUserException.class, () -> userService.updateUserById(user, user.getId()));

    }
    @Test
    void updateUserWithInvalidBodyRequestException() {
        User user = new User();
        user.setId(1L);
        user.setName("TEST");
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        when(userRepository.existsById(user.getId())).thenReturn(Boolean.TRUE);
        when(userRepository.getReferenceById(user.getId())).thenReturn(user);
        Assertions.assertThrows(UserUpdateWithInvalidBodyException.class, () -> userService.updateUserById(user, 1L));

    }

    @Test
    void deleteUserShouldSuccess(){
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setEmail("email");
        user.setPassword("pswrd");

        when(userRepository.existsById(user.getId())).thenReturn(Boolean.TRUE);

        Assertions.assertNotNull(user);
    }

    @Test
    void deleteUserShouldBadRequest(){
        User user = new User();
        user.setId(1L);
        user.setName("test");
        user.setEmail("email");
        user.setPassword("pswrd");

        when(userRepository.existsById(user.getId())).thenReturn(Boolean.FALSE);

        Assertions.assertThrows(InvalidUserException.class, ()-> userService.deleteUserById(user.getId()));
    }

}
