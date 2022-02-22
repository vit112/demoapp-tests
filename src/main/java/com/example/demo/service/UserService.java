package com.example.demo.service;

import com.example.demo.entity.TodoItem;
import com.example.demo.entity.User;

public interface UserService {

    User getUserById(int userId);
    void deleteUserById(int userId);
    User addUser(User user);
    void saveTodoItem(int userId, TodoItem todoItem);
    void updateUser(int userId, User user);


    //List<User> getAllUsers();
    //User addUser(User user);
}
