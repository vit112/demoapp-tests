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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.text.ParseException;

import static com.example.demo.prototype.TodoItemPrototype.*;
import static com.example.demo.prototype.UserPrototype.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


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

      given(userController.getUserById(1)).willReturn(aUser());

        MockHttpServletResponse response = mvc.perform(get("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonUser.write(aUser()).getJson());

    }


    @Test //passed
    void addUser() throws Exception {

        given(userController.addUser(aUser2())).willReturn(aUser2());

        MockHttpServletResponse response = mvc.perform(
                post("/users").contentType(MediaType.APPLICATION_JSON).content(
                        jsonUser.write(aUser2()).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(userController, times(1)).addUser(aUser2());

    }



    //passed
    @Test
    void addTodoItem() throws Exception {

        doNothing().when(userController).addTodoItem(anyInt(), any(TodoItem.class));
       //userController.addTodoItem(aUser().getId(), aTodoItem3());
        MockHttpServletResponse response = mvc.perform(
                post("/users/2/todoItems").contentType(MediaType.APPLICATION_JSON).content(
                        jsonTodoItem.write(aTodoItem2()).getJson()))
                        .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(userController, times(1)).addTodoItem(2, aTodoItem2());
    }



    @Disabled
    @Test
    void deleteUser() throws Exception {
        doNothing().when(userController).deleteUser(aUser3().getId());
        MockHttpServletResponse response = mvc.perform(
                delete("/users/1")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(userController, times(1)).deleteUser(aUser3().getId());

    }

    @Disabled
    @Test
    void deleteTodoItem() throws Exception {
        doNothing().when(userController).deleteTodoItem(1, 1);

        MockHttpServletResponse response = mvc.perform(
                delete("/users/1/todoItem/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(userController, times(1)).deleteTodoItem(1,1);

    }


}