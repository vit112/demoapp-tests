package com.example.demo.restcontroller;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

import static com.example.demo.prototype.UserPrototype.aUser;
import static com.example.demo.prototype.UserPrototype.aUser2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerSpringBootTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Disabled
    @Test //passed
    void addUser() throws Exception {

        //when
        ResponseEntity<User> userResponseEntity = restTemplate.postForEntity("/users",
                aUser2(), User.class);
        // then
        assertThat(userResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Disabled
    @Test
    void deleteUser() {
    }


    }



