package com.example.demo.service;

import com.example.demo.entity.TodoItem;
import com.example.demo.entity.User;
import com.example.demo.repository.TodoItemRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoItemRepository todoItemRepository;
    @Autowired
    private  TodoItemService todoItemService;


    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public void updateUser(int userId, User user) {
        User userDB = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        userDB.setUsername(user.getUsername());
        userDB.setPassword(user.getPassword());
        try {
            userRepository.save(user);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteUserById(int userId) {
        try {
            userRepository.deleteById(userId);
        } catch (DataAccessException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public User addUser(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        try {
            userRepository.save(newUser);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
        return newUser;
    }

    public void saveTodoItem(int userId, TodoItem todoItem){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("todoItemId not found"));
       TodoItem newItem = todoItemService.addTodoItem(todoItem);
        user.getTodoList().add(newItem);
        userRepository.save(user);
    }
/*
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User addUser(User user) {
    return userRepository.save(user);
    }
 */
}


