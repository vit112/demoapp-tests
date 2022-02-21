package com.example.demo.restcontroller;

import com.example.demo.entity.TodoItem;
import com.example.demo.entity.User;
import com.example.demo.service.TodoItemService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TodoItemService todoItemService;


    //get user based on id
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    //add User
    @PostMapping
    public User addUser(@Valid @NotNull @RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/{userId}/todoItems")
    public void addTodoItem(@PathVariable int userId, @Valid @NotNull @RequestBody TodoItem todoItem) {
        userService.saveTodoItem(userId, todoItem);
    }

    //complete the tasks of specified todoItem
    @PostMapping("/todoItem/{todoItemId}")
    public void completeTask(@PathVariable int todoItemId){
        todoItemService.completeTask(todoItemId);
    }

    //delete todoItem based on specified id
    @DeleteMapping("{userId}/todoItem/{todoItemId}")
    public void deleteTodoItem(@PathVariable int userId, @PathVariable int todoItemId){
        todoItemService.deleteTodoItem(userId, todoItemId);
    }

    //delete user based on specified id
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId){
        userService.deleteUserById(userId);
    }
    //Update existing user
    @PutMapping("/{userId}")
    public void updateUser(@PathVariable int userId, @RequestBody @Valid @NotNull User user){
        userService.updateUser(userId, user);
    }

}
