package com.example.demo.prototype;

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
            t.setId(1);
            t.setTitle("todoItem_test");
            t.setBody("body");
            //Date date = DateFor.parse("20/02/2022");
           // t.setDueDate(date);
            t.setDone(true);
            return t;
        }

        public static TodoItem aTodoItem3() throws ParseException {
            TodoItem t = new TodoItem();
            t.setTitle("todoItem_test");
            t.setBody("body");
            Date date = DateFor.parse("20/02/2022");
            t.setDueDate(date);
            return t;
        }
}
