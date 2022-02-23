package com.example.demo.service;

import com.example.demo.entity.TodoItem;
import com.example.demo.entity.User;
import com.example.demo.repository.TodoItemRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class TodoItemServiceImpl implements TodoItemService {

    @Autowired
    TodoItemRepository todoItemRepository;
    @Autowired
    UserRepository userRepository;


    @Override
    public TodoItem addTodoItem(TodoItem todoItem) {
        TodoItem newTodoItem = new TodoItem();
        newTodoItem.setTitle(todoItem.getTitle());

        if(!todoItem.getBody().isEmpty()) {
            newTodoItem.setBody(todoItem.getBody());
        }
        newTodoItem.setDueDate(todoItem.getDueDate());
        try {
            todoItemRepository.save(newTodoItem);
        } catch (DataAccessException e){
           throw new RuntimeException(e.getMessage());
        }
        return newTodoItem;
    }

    @Override
    public List<TodoItem> getUserTodoItems(int id){
        return userRepository.getById(id).getTodoList();
    }

    @Override
    public List<TodoItem> getTodoItems() {
        return todoItemRepository.findAll();
    }



    @Override
    public List<TodoItem> getTodoItemsSortedByDate() {
        return todoItemRepository.findAllByOrderByDueDateAsc();
    }

    @Override
    public void completeTask(int todoItemId){
        TodoItem theTodoItem = todoItemRepository.findById(todoItemId).orElseThrow(()
                -> new NoSuchElementException("todoItemId not found"));
        if(theTodoItem.isDone() == false){
            theTodoItem.setDone(true);
            todoItemRepository.save(theTodoItem);
        }
    }

    @Override
    public void deleteTodoItem(int userId,int todoItemId){
        User user = userRepository.findById(userId).orElseThrow();
        TodoItem todoItem = todoItemRepository.findById(todoItemId).orElseThrow(()
        -> new NoSuchElementException("todoItemId not found"));
        user.getTodoList().remove(todoItem);
        try {
            todoItemRepository.delete(todoItem);
        } catch (DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

}



