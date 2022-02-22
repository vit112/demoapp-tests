package com.example.demo.repository;

import com.example.demo.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

//@RepositoryRestResource(path="items")
public interface TodoItemRepository extends JpaRepository<TodoItem, Integer>{
    List<TodoItem> findAllByOrderByDueDateAsc();

}
