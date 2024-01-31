package com.usersDb.usersDb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usersDb.usersDb.model.User;
import com.usersDb.usersDb.repository.UserRepository;
import com.usersDb.usersDb.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;


    @Test
    public void createUserShouldSuccess() throws Exception {
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
                .andExpect(status().isOk());
    }



    @Test
    public void findUserByIdShouldSuccess() throws Exception {
        User user = new User();
        user.setId(10L);
        user.setName("asdasda");
        user.setEmail("aaa@gmail.com");
        user.setPassword("test123");

        when(userRepository.getReferenceById(10L)).thenReturn(user);
        when(userRepository.existsById(10L)).thenReturn(Boolean.TRUE);

        mvc.perform(get("/user/{userId}", user.getId()))
                .andExpect(status().isOk());
    }



    @Test
    public void updateUserShouldSucess() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Tiago");
        user.setEmail("test@gmail.com");
        user.setPassword("Test");

        user.setName("Modify");
        user.setPassword("NEW PASSWORD");

        when(userRepository.getReferenceById(1L)).thenReturn(user);
        when(userRepository.existsById(1L)).thenReturn(Boolean.TRUE);

        ObjectMapper objectMapper = new ObjectMapper();
        String userObject = objectMapper.writeValueAsString(user);

        mvc.perform(put("/user/{userId}",user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userObject))
                .andExpect(status().isOk());
    }


    @Test
    public void updateUserDetailShouldSuccess() throws Exception {
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

        mvc.perform(patch("/user/{userId}",user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userObject))
                .andExpect(status().isOk());

    }


    @Test
    public void deleteUserShouldSuccess() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("Tiago");
        user.setEmail("test@gmail.com");
        user.setPassword("Test");

        doNothing().when(userRepository).deleteById(user.getId());
        when(userRepository.existsById(4L)).thenReturn(Boolean.TRUE);

        mvc.perform(delete("/user/{userId}",user.getId()))
                .andExpect(status().isOk());

    }


}
