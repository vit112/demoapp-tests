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


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {

	

	}
}
