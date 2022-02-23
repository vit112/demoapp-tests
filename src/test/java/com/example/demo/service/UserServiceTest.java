package com.example.demo.service;

import com.example.demo.repository.TodoItemRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.demo.prototype.TodoItemPrototype.*;
import static com.example.demo.prototype.UserPrototype.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import com.example.demo.entity.TodoItem;
import com.example.demo.entity.User;


import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private TodoItemRepository todoItemRepository;
    private TodoItemService todoItemService;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        todoItemRepository = mock(TodoItemRepository.class);
        userService = new UserServiceImpl(userRepository, todoItemRepository, todoItemService);
    }


    @Test
    void getUserById() throws ParseException {
        UserService userService = mock(UserService.class);
        when(userService.getUserById(aUser().getId())).thenReturn(aUser());
        User createdUser = new User();
        createdUser = userService.getUserById(aUser().getId());
        assertEquals(createdUser, aUser());

    }

    @Test
    void getUserByIdException() {
        UserService userService = mock(UserService.class);
        doThrow(new NoSuchElementException("No user found"))
                .when(userService)
                .getUserById(anyInt());
        assertThrows(NoSuchElementException.class, () -> userService.getUserById(1));
    }

    @Test
    void addUser() throws ParseException {
        UserService userService = mock(UserService.class);

        when(userService.addUser(aUser3())).thenReturn(aUser3());
        User createdUser = new User();
        createdUser = userService.addUser(aUser3());
        assertEquals(createdUser, aUser3());
    }

    @Test
    void addUserException() throws ParseException {
        UserService userService = mock(UserService.class);

        doThrow(new RuntimeException("user not added"))
                .when(userService)
                .addUser(aUser());
        assertThrows(RuntimeException.class, () -> userService.addUser(aUser()));

    }

    @Test
    void deleteUserById() {
        UserService userService = mock(UserService.class);

        doNothing().when(userService).deleteUserById(1);
        userService.deleteUserById(1);
        verify(userService, times(1)).deleteUserById(1);
    }

    @Test
    void deleteUserByIdException() {
        UserService userService = mock(UserService.class);

        doThrow(new RuntimeException("user not deleted"))
                .when(userService)
                .deleteUserById(anyInt());
        assertThrows(RuntimeException.class, () -> userService.deleteUserById(1));
    }


    @Test
    void saveTodoItem() throws ParseException {
        UserService userService = mock(UserService.class);
        doNothing().when(userService).saveTodoItem(1, aTodoItem());
        userService.saveTodoItem(1, aTodoItem());
        verify(userService, times(1)).saveTodoItem(1, aTodoItem());

    }

    @Test
    void saveTodoItemTest() throws ParseException {
        UserService userService = mock(UserService.class);
        doThrow(new NoSuchElementException("todoItem not saved"))
                .when(userService)
                .saveTodoItem(1, aTodoItem3());
        assertThrows(NoSuchElementException.class, () -> userService.saveTodoItem(1, aTodoItem3()));

    }

    @Test
    void updateUser() throws ParseException {
        UserService userService = mock(UserService.class);
        doNothing().when(userService).updateUser(1, aUser());
        userService.updateUser(1, aUser());
        verify(userService, times(1)).saveTodoItem(1, aTodoItem());
    }

    @Test
    void updateUserException() throws ParseException {
        UserService userService = mock(UserService.class);
        doThrow(new RuntimeException("user not update"))
                .when(userService)
                .updateUser(1, aUser());
        assertThrows(RuntimeException.class, () -> userService.updateUser(1, aUser()));
    }

}