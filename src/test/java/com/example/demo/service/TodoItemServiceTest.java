package com.example.demo.service;

import com.example.demo.entity.TodoItem;
import com.example.demo.entity.User;
import com.example.demo.repository.TodoItemRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.prototype.TodoItemPrototype.aTodoItem;
import static com.example.demo.prototype.TodoItemPrototype.aTodoItem2;
import static com.example.demo.prototype.UserPrototype.aUser;
import static com.example.demo.prototype.UserPrototype.aUser3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TodoItemServiceTest {

    private UserRepository userRepository;
    private TodoItemRepository todoItemRepository;
    private TodoItemService todoItemService;

    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        todoItemRepository = mock(TodoItemRepository.class);
        todoItemService = new TodoItemServiceImpl(todoItemRepository, userRepository);
    }



    @Test //passed
    void addTodoItem() throws ParseException {
        when(todoItemRepository.save(any(TodoItem.class))).thenReturn(aTodoItem());
        TodoItem createdTodoItem = todoItemService.addTodoItem(aTodoItem());
        assertThat(createdTodoItem).isNotNull();
        assertThat(createdTodoItem.getTitle()).isEqualTo(aTodoItem().getTitle());
    }

    @Test //passed
    void getTodoItems() throws ParseException {
        List<TodoItem> todoItems = Arrays.asList(aTodoItem());
        given(todoItemService.getTodoItems()).willReturn(todoItems);

        List<TodoItem> expected = todoItemService.getTodoItems();
        assertEquals(expected, todoItems);
        verify(todoItemRepository).findAll();
    }


    @Test //passed
    void getUserTodoItems() throws ParseException {

        List<TodoItem> todoItems = Arrays.asList(aTodoItem(), aTodoItem2());
        when(userRepository.save(any())).thenReturn(aUser());
        User createdUser = aUser();
        assertEquals(todoItems, aUser().getTodoList());
    }

    @Disabled
    @Test //does not work yet
    void deleteTodoItem() throws ParseException {
        //when(todoItemService.deleteTodoItem(aUser().getId(), aTodoItem().getId())).thenReturn(aTodoItem());
        //verify(todoItemRepository,times(1)).findAll();
    }

    @Disabled
    @Test //does not work yet
    void completeTask() throws ParseException {

        //given(todoItemService.completeTask(aTodoItem().getId())).willAnswer(aTodoItem().setDone(true));
        //todoItemService.completeTask(aTodoItem().getId());

        // when(todoItemRepository.getById(any())).thenReturn(aTodoItem2());

        // when(todoItemRepository.getById(any())).thenReturn(aTodoItem().setDone(true));

        //given(todoItemService.completeTask(aTodoItem().getId())).willAnswer(aTodoItem().isDone(),true);
        assertEquals(aTodoItem(), aTodoItem2());
        //   assertEquals(todoItemRepository.getById(aTodoItem().getId()).setDone(true), aTodoItem2());
    }



}

