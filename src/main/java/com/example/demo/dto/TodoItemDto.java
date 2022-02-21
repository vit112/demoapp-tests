package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemDto {


    private int id;
    private String title;
    private String body;
    private Date dueDate;
    private boolean done;
}
