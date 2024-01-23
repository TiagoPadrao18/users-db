package com.usersDb.usersDb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usersDb.usersDb.exception.AlreadyExistEmailException;
import com.usersDb.usersDb.exception.UserCreationException;
import com.usersDb.usersDb.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void createUserWithNoParamTest() {
        try {
            User user = new User();
            user.setName("adaadada");
            user.setEmail("dadsaasdasdadsaad@gmail.com");
            user.setPassword("test123");

            ObjectMapper objectMapper = new ObjectMapper();
            String userObject = objectMapper.writeValueAsString(user);

            mvc.perform(post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(userObject))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new UserCreationException();
        }
    }


    @Test
    public void createUserWitExistEmailTest() {
        try {
            User user = new User();
            user.setName("asda");
            user.setEmail("marcospapapadasdasdaaa.mindera.com");
            user.setPassword("teste");

            ObjectMapper objectMapper = new ObjectMapper();
            String userObject = objectMapper.writeValueAsString(user);

            mvc.perform(post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(userObject))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new AlreadyExistEmailException("This email already Exists");
        }


    }

}