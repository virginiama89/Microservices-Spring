package com.vir.microservices.boot.lab1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Lab1Controller {

	@GetMapping("/lab1")
	public String lab1() {
		return "lab1template";
	}
}
