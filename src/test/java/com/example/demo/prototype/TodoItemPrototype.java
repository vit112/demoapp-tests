package com.example.demo.prototype;

import com.example.demo.dto.TodoItemDto;
import com.example.demo.entity.TodoItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



    public class TodoItemPrototype {
        static SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");

        public static TodoItem aTodoItem() throws ParseException {
            TodoItem t = new TodoItem();
            t.setId(1);
            t.setTitle("todoItem_test");
            t.setBody("body");
            Date date = DateFor.parse("20/02/2022");
            t.setDueDate(date);
            t.setDone(false);
            return t;
        }

        public static TodoItem aTodoItem2() throws ParseException {
            TodoItem t = new TodoItem();
            t.setId(2);
            t.setTitle("todoItem_test2");
            t.setBody("body2");
            Date date = DateFor.parse("21/02/2022");
            t.setDueDate(date);
            t.setDone(false);
            return t;
        }

        public static TodoItemDto atodoItemDto() throws ParseException {
            Date date = DateFor.parse("20/02/2022");
            return atodoItemDto().builder()
                    .id(1)
                    .title("todoItem_test")
                    .body("body")
                    .dueDate(date)
                    .done(false)
                    .build();
        }
}
