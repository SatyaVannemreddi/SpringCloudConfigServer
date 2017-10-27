package com.satya.sc.cc.control;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientRService {
	@Value("${FirstName}")
	String firstName;
	
	@Value("${LastName}")
	String lastName;
	
	@GetMapping("/hello")
	public String sayHello(){
		return "Hello "+firstName+" "+lastName;
	}

}
