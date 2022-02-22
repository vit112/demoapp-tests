package com.example.demo;

import com.example.demo.entity.TodoItem;
import com.example.demo.entity.User;
import com.example.demo.repository.TodoItemRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

//Command Line Runner is an interface that used to execute the code after the Spring Boot application started
@SpringBootApplication
public class DemoApplication  implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TodoItemRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {

		//not fixed yet

		//hardcoded user 1
		User user = new User();
		user.setPassword("password");
		user.setUsername("Vit");
		TodoItem todoItem = new TodoItem();
		todoItem.setTitle("learn Spring");
		todoItem.setBody("udemy");
		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
		Date date = DateFor.parse("20/02/2022");
		todoItem.setDueDate(date);
		user.getTodoList().add(todoItem);
		todoRepository.save(todoItem);
		TodoItem todoItem4 = new TodoItem();
		todoItem4.setTitle("go jogging");
		todoItem4.setBody("5km");
		Date date4 = DateFor.parse("22/03/2022");
		todoItem4.setDueDate(date4);
		user.getTodoList().add(todoItem4);
		todoRepository.save(todoItem4);
		TodoItem todoItem5 = new TodoItem();
		todoItem5.setTitle("groceries shopping");
		todoItem5.setBody("banana, beef");
		todoItem5.setDone(true);
		user.getTodoList().add(todoItem5);
		todoRepository.save(todoItem5);
		userRepository.save(user);


		//hardcoded user 2
		User user2 = new User();
		user2.setPassword("password");
		user2.setUsername("Adam");
		TodoItem todoItem2 = new TodoItem();
		todoItem2.setTitle("learn Angluar");
		todoItem2.setBody("Something");
		Date date2 = DateFor.parse("22/03/2022");
		todoItem2.setDueDate(date2);
		todoItem2.setDone(true);
		user2.getTodoList().add(todoItem2);
		todoRepository.save(todoItem2);
		userRepository.save(user2);

		//hardcoded user 3
		User user3 = new User();
		user3.setPassword("password");
		user3.setUsername("Zoro");
		TodoItem todoItem3 = new TodoItem();
		todoItem3.setTitle("go jogging");
		todoItem3.setBody("10km");
		Date date3 = DateFor.parse("22/02/2022");
		todoItem3.setDueDate(date3);
		user3.getTodoList().add(todoItem3);
		todoRepository.save(todoItem3);
		userRepository.save(user3);



	}
}
