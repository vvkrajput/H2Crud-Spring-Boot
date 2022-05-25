package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
	@RequestMapping(method=RequestMethod.GET, path="/hellos")
	public String Hello() {
		return "Hello World";
	}
	

	

}
