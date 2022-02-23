package com.example.demo.service;

import com.example.demo.entity.TodoItem;
import com.example.demo.entity.User;
import com.example.demo.repository.TodoItemRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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



    void addTodoItem() throws ParseException {
        TodoItemService todoItemService = mock(TodoItemService.class);
        when(todoItemService.addTodoItem(any(TodoItem.class))).thenReturn(aTodoItem());
        TodoItem createdTodoItem = todoItemService.addTodoItem(aTodoItem());
        assertThat(createdTodoItem).isNotNull();
        assertThat(createdTodoItem.getTitle()).isEqualTo(aTodoItem().getTitle());
    }
    @Test
    void addTodoItemException(){
        TodoItemService todoItemService = mock(TodoItemService.class);
        doThrow(new RuntimeException("Item not added"))
                .when(todoItemService)
                .addTodoItem(any(TodoItem.class));
        assertThrows(RuntimeException.class, () -> todoItemService.addTodoItem(aTodoItem()));
    }

    @Test
    void getTodoItems() throws ParseException {
        List<TodoItem> todoItems = Arrays.asList(aTodoItem());
        given(todoItemService.getTodoItems()).willReturn(todoItems);

        List<TodoItem> expected = todoItemService.getTodoItems();
        assertEquals(expected, todoItems);

    }



    @Test
    void getUserTodoItems() throws ParseException {
        TodoItemService todoItemService = mock(TodoItemService.class);
        List<TodoItem> todoItems = Arrays.asList(aTodoItem(), aTodoItem2());
        when(todoItemService.getUserTodoItems(anyInt())).thenReturn(todoItems);
               List<TodoItem> expected = todoItemService.getUserTodoItems(1);
                assertEquals(todoItems, expected);
    }


    @Test
    void completeTask() {
        TodoItemService todoItemService = mock(TodoItemService.class);
        doNothing().when(todoItemService).completeTask(anyInt());
        todoItemService.completeTask(1);
        verify(todoItemService, times(1)).completeTask(1);
    }

    @Test
    void completeTaskException() {
        TodoItemService todoItemService = mock(TodoItemService.class);
        doThrow(new RuntimeException())
                .when(todoItemService)
                .completeTask(anyInt());
        assertThrows(RuntimeException.class, () -> todoItemService.completeTask(1));
    }

    @Test
    void deleteTodoItem() {
        TodoItemService todoItemService = mock(TodoItemService.class);
        doNothing().when(todoItemService).deleteTodoItem(anyInt(),anyInt());
        todoItemService.deleteTodoItem(1,1);
        verify(todoItemService, times(1)).deleteTodoItem(1,1);
    }

    @Test
    void deleteTodoItemException() {
        TodoItemService todoItemService = mock(TodoItemService.class);
        doThrow(new RuntimeException())
                .when(todoItemService)
                .deleteTodoItem(anyInt(),anyInt());
        assertThrows(RuntimeException.class, () -> todoItemService.deleteTodoItem(1,1));
    }
}

