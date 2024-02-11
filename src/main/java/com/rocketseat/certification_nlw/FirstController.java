package com.rocketseat.certification_nlw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firstController")
public class FirstController {
	
	@GetMapping("/returnFirstController")
	public String returnFirstController() {
		return "Criando a primeira controller";
	}

}
