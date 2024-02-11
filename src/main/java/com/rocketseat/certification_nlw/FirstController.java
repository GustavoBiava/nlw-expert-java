package com.rocketseat.certification_nlw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firstController")
public class FirstController {
	
	@GetMapping("/returnFirstController")
	public User returnFirstController() {
		var user = new User("Cristiano", 39);
		return user;
	}
	
	@PostMapping("/firstPost")
	public String firstPost() {
		return "Primeiro post";
	}
	
	record User(String name, Integer age) {}

}
