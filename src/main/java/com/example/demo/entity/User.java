package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //auto increment
    private int id;
    @NotBlank
    @Column(name="username")
    private String username;
    @NotBlank
    @Column(name="password")
    private String password;

    @OneToMany(cascade = {CascadeType.REMOVE}) //remove user's todoItems if user removed
    @JoinColumn(name = "user_fid", referencedColumnName = "id")
    private List<TodoItem> todoList = new ArrayList<>();

    public User(String username, String password, List<TodoItem> todoList) {
        this.username = username;
        this.password = password;
        this.todoList = todoList;
    }


}
