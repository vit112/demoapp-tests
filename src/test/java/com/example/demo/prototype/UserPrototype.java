package com.example.demo.prototype;

import com.example.demo.entity.TodoItem;
import com.example.demo.entity.User;

import java.text.ParseException;
import java.util.List;

import static com.example.demo.prototype.TodoItemPrototype.aTodoItem;
import static com.example.demo.prototype.TodoItemPrototype.aTodoItem2;

public class UserPrototype {


    public static User aUser() throws ParseException {
        User u = new User();
        u.setUsername("user1");
        u.setPassword("pass1");

        u.getTodoList().add(aTodoItem());
        u.getTodoList().add(aTodoItem2());
        return u;
    }

    public static User aUser2() throws ParseException {
        User u = new User();
        u.setUsername("user1");
        u.setPassword("pass1");
        return u;
    }

    public static User aUser3() throws ParseException {
        User u = new User();
        u.setId(1);
        u.setUsername("user1");
        u.setPassword("pass1");
        return u;
    }
}

