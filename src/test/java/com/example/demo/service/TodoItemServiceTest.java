package com.example.demo.service;

import com.example.demo.dto.TodoItemDto;
import com.example.demo.entity.TodoItem;
import com.example.demo.repository.TodoItemRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.prototype.TodoItemPrototype.aTodoItem;
import static com.example.demo.prototype.TodoItemPrototype.atodoItemDto;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TodoItemServiceTest {

    private UserRepository userRepository;
    private TodoItemRepository todoItemRepository;
    //private UserServiceImpl userService;
    private TodoItemService todoItemService;

    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        todoItemRepository = mock(TodoItemRepository.class);
        todoItemService = new TodoItemServiceImpl(todoItemRepository, userRepository);
    }



    @Test
    void addTodoItem() throws ParseException {
        when(todoItemRepository.save(any(TodoItem.class))).thenReturn(aTodoItem());
        TodoItem createdTodoItem = todoItemService.addTodoItem(aTodoItem());
        assertThat(createdTodoItem).isNotNull();
        assertThat(createdTodoItem.getTitle()).isEqualTo(aTodoItem().getTitle());
    }
/*
    @Test
    void getTodoItems() throws ParseException {
        List<TodoItem> todoItems = Arrays.asList(aTodoItem());
        given(todoItemService.getTodoItems()).willReturn(todoItems);

        List<TodoItem> expected = todoItemService.getTodoItems();
        assertEquals(expected, todoItems);
        verify(todoItemRepository).findAll();
    }



    @Disabled
    @Test //does not work
    void completeTask() throws ParseException {
        todoItemService.completeTask(aTodoItem().getId());  //java.util.NoSuchElementException: todoItemId not found (null)

        //given(todoItemService.completeTask(aTodoItem().getId())).willAnswer(aTodoItem().isDone(),true);
        verify(todoItemRepository).getById(aTodoItem().getId()).setDone(true);
    }
    @Disabled
    @Test //does not work
    void getUserTodoItems() throws ParseException {
        List<TodoItem> todoItems = Arrays.asList(aTodoItem(), aTodoItem2());
        aUser().getTodoList().add(aTodoItem());
        aUser().getTodoList().add(aTodoItem2());
        given(todoItemService.getUserTodoItems(aUser().getId())).willReturn(todoItems); //getUserTodoItems returns null
        //verify(todoItemRepository).
    }


    @Test
    void deleteTodoItem() {


    }

    @Test
    void getTodoItemsSortedByDate() {
    }


}
     */
}