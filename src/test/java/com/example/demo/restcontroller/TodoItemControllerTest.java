package com.example.demo.restcontroller;

import com.example.demo.entity.TodoItem;

import com.example.demo.service.TodoItemService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.prototype.TodoItemPrototype.*;
import static com.example.demo.prototype.UserPrototype.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class TodoItemControllerTest {

    @Mock
    TodoItemController todoItemController;
    ObjectMapper objectMapper;
    MockMvc mvc;
    @Mock
    TodoItemService todoItemService;
    @Mock
    UserController userController;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(todoItemController)
                .build();
    }

    @Test
    void getTodoItems() throws Exception {
        List<TodoItem> todoItemList = Arrays.asList(aTodoItem2(), aTodoItem());
        given(todoItemController.getTodoItems()).willReturn(todoItemList);


        MockHttpServletResponse response = mvc.perform(
                get("/todoItems").accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(todoItemController, times(1)).getTodoItems();
    }


    @Test
    void getUserTodoItems() throws Exception {
        List<TodoItem> todoItemList = Arrays.asList(aTodoItem(),aTodoItem2());
        given(todoItemController.getUserTodoItems(anyInt())).willReturn(todoItemList);

        MockHttpServletResponse response = mvc.perform(
                        get("/todoItems/user/1").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(todoItemController, times(1)).getUserTodoItems(1);
    }

}