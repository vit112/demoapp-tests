package com.example.demo.prototype;

import com.example.demo.entity.User;

public class UserPrototype {

    public static User aUser(){
        User u = new User();
        u.setUsername("user1");
        u.setPassword("pass1");
        return u;
    }
}

