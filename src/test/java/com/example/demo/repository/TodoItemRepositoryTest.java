package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.example.demo.prototype.TodoItemPrototype.aTodoItem;
import static com.example.demo.prototype.TodoItemPrototype.aTodoItem2;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TodoItemRepositoryTest {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Test
    void findAllByOrderByDueDateAsc() throws ParseException {
        //when
        int expected = todoItemRepository.getById(aTodoItem().getId()).getDueDate().compareTo(todoItemRepository.getById(aTodoItem2().getId()).getDueDate());
        //then
        assertThat(expected).isEqualTo(-1);
    }
}