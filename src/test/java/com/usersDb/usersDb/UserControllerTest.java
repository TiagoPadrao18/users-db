package com.usersDb.usersDb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usersDb.usersDb.model.User;
import com.usersDb.usersDb.repository.UserRepository;
import com.usersDb.usersDb.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;


    @Test
    void createUserShouldSuccess() throws Exception {
        User user = new User();
        user.setName("asdasda");
        user.setEmail("aaa@gmail.com");
        user.setPassword("test123");

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        when(userRepository.save(user)).thenReturn(user);
        mvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated());
    }


    @Test
    void createUserWithNoParamShouldBadRequest() throws Exception {
        User user = new User();


        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        when(userRepository.save(user)).thenReturn(user);
        mvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }


    @Test
    void createUserWithExistEmailShouldBadRequest() throws Exception {

        User user = new User();
        user.setName("asdad");
        user.setEmail("test@gmail.com");
        user.setPassword("adsadadsa");

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(Boolean.TRUE);

        mvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }


    @Test
    void findUserByIdShouldSuccess() throws Exception {
        User user = new User();
        user.setId(10L);
        user.setName("asdasda");
        user.setEmail("aaa@gmail.com");
        user.setPassword("test123");

        when(userRepository.existsById(user.getId())).thenReturn(Boolean.TRUE);

        mvc.perform(get("/user/{userId}", user.getId()))
                .andExpect(status().isOk());
    }


    @Test
    void findUserByIdShouldBadRequest() throws Exception {
        User user = new User();
        user.setId(10L);
        user.setName("asdasda");
        user.setEmail("aaa@gmail.com");
        user.setPassword("test123");

        when(userRepository.existsById(user.getId())).thenReturn(Boolean.FALSE);

        mvc.perform(get("/user/{userId}", user.getId()))
                .andExpect(status().isNotFound());
    }


    @Test
    void updateUserShouldSuccess() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Tiago");
        user.setEmail("test@gmail.com");
        user.setPassword("Test");

        user.setName("Taasdad");

        user.setPassword("test");
        when(userRepository.existsById(user.getId())).thenReturn(Boolean.TRUE);
        when(userRepository.getReferenceById(user.getId())).thenReturn(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String userObject = objectMapper.writeValueAsString(user);

        mvc.perform(put("/user/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userObject))
                .andExpect(status().isOk());
    }


    @Test
    void updateUserDetailShouldSuccess() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Tiago");
        user.setEmail("test@gmail.com");
        user.setPassword("Test");

        user.setPassword("NEW PASSWORD");

        when(userRepository.getReferenceById(1L)).thenReturn(user);
        when(userRepository.existsById(1L)).thenReturn(Boolean.TRUE);

        ObjectMapper objectMapper = new ObjectMapper();
        String userObject = objectMapper.writeValueAsString(user);

        mvc.perform(patch("/user/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userObject))
                .andExpect(status().isOk());

    }


    @Test
    void updateUserShouldNotFound() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Tiago");
        user.setEmail("test@gmail.com");
        user.setPassword("Test");


        when(userRepository.existsById(user.getId())).thenReturn(Boolean.FALSE);

        ObjectMapper objectMapper = new ObjectMapper();
        String userObject = objectMapper.writeValueAsString(user);

        mvc.perform(put("/user/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userObject))
                .andExpect(status().isBadRequest());
    }


    @Test
    void updateUserWithInvalidBodyRequestException() throws Exception {
        User user = Mockito.mock(User.class);
        user.setId(1L);
        user.setName("Tiago");
        user.setEmail("test@gmail.com");
        user.setPassword("Test");


        when(userRepository.existsById(user.getId())).thenReturn(Boolean.TRUE);
        when(user.getName()).thenReturn(null);
        when(user.getPassword()).thenReturn(null);
        ObjectMapper objectMapper = new ObjectMapper();
        String userObject = objectMapper.writeValueAsString(user);

        mvc.perform(put("/user/{userId}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userObject))
                .andExpect(status().isBadRequest());
    }


    @Test
    void deleteUserShouldSuccess() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Tiago");
        user.setEmail("test@gmail.com");
        user.setPassword("Test");


        when(userRepository.existsById(user.getId())).thenReturn(Boolean.TRUE);

        mvc.perform(delete("/user/{userId}", user.getId()))
                .andExpect(status().isOk());

    }


    @Test
    void deleteUserShouldBadRequest() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Tiago");
        user.setEmail("test@gmail.com");
        user.setPassword("Test");

        when(userRepository.existsById(user.getId())).thenReturn(Boolean.FALSE);

        mvc.perform(delete("/user/{userId}", user.getId()))
                .andExpect(status().isBadRequest());
    }


    @Test
    void getAllUsersShouldSuccess() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(new User());

        mvc.perform(get("/user"))
                .andExpect(status().isOk());
    }


}
