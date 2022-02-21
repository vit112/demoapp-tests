package com.example.demo.restcontroller;

import com.example.demo.service.TodoItemService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/todoItems")
public class TodoItemController {


   @Autowired
   private UserService userService;
   @Autowired
   private TodoItemService todoItemService;

    //get all todoItems
    @GetMapping
    public List getTodoItems() {
        return todoItemService.getTodoItems();
    }
    //sort all todoItems by dueDate
    @GetMapping("/sortedByDate")
    public List getTodoItemsSortedByDate() {
        return todoItemService.getTodoItemsSortedByDate();
    }

    //get user's todoItems
    @GetMapping("/user/{userId}")
    public List getUserTodoItems(@PathVariable @Valid @NotNull int userId){
        return todoItemService.getUserTodoItems(userId);
    }
}


