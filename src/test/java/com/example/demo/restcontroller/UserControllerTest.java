package com.example.demo.restcontroller;

import com.example.demo.entity.TodoItem;
import com.example.demo.entity.User;
import com.example.demo.prototype.UserPrototype;
import com.example.demo.service.TodoItemService;
import com.example.demo.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.text.ParseException;

import static com.example.demo.prototype.TodoItemPrototype.aTodoItem;
import static com.example.demo.prototype.TodoItemPrototype.aTodoItem3;
import static com.example.demo.prototype.UserPrototype.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    private MockMvc mvc;
    ObjectMapper objectMapper; //convert objects to json
    @Mock
    private UserService userService;
    @Mock
    private TodoItemService todoItemService;

    @Mock
    private UserController userController;

    private JacksonTester<User> jsonUser;
    private  JacksonTester<TodoItem> jsonTodoItem;
    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }


    @Test //passed
    public void getUserById() throws Exception {
        //given
      given(userController.getUserById(1)).willReturn(aUser());
      //when
        MockHttpServletResponse response = mvc.perform(get("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonUser.write(aUser()).getJson());

    }


    @Test //passed
    void addUser() throws Exception {

        given(userController.addUser(aUser2())).willReturn(aUser2());
        //when
        MockHttpServletResponse response = mvc.perform(
                post("/users").contentType(MediaType.APPLICATION_JSON).content(
                        jsonUser.write(aUser2()).getJson()))
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    //passed
    @Test
    void addTodoItem() throws Exception {

        doNothing().when(userController).addTodoItem(aUser().getId(), aTodoItem3());
        userController.addTodoItem(aUser().getId(), aTodoItem3());
        MockHttpServletResponse response = mvc.perform(
                post("/users/1/todoItems").contentType(MediaType.APPLICATION_JSON).content(
                        jsonTodoItem.write(aTodoItem3()).getJson()))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(userController, times(1)).addTodoItem(aUser().getId(), aTodoItem3());
    }

    @Disabled
    @Test
    void deleteTodoItem() {
    }

    @Disabled
    @Test
    void updateUser() {
    }

}