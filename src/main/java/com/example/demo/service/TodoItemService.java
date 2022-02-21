package com.example.demo.service;

import com.example.demo.entity.TodoItem;

import java.util.Date;
import java.util.List;

public interface TodoItemService {

    List<TodoItem> getTodoItems();
    TodoItem addTodoItem(TodoItem todoItem);
    void completeTask(int todoItemId);
    void deleteTodoItem(int userId,int todoItemId);
    List<TodoItem> getTodoItemsSortedByDate();
    List<TodoItem> getUserTodoItems(int id);

    // TodoItem addTodoItem(TodoItem todoItem);
    // TodoItem getTodoItemById(int todoItemId);
    //void updateTodoItem(TodoItem todoItem);
    // void deleteTodoItem(int todoItemId);
}
