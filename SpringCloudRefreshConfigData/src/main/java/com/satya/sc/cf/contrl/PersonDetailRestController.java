package com.satya.sc.cf.contrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satya.sc.cf.cnf.PersonDetails;

@RestController
@RefreshScope
public class PersonDetailRestController {
	
	@Autowired
	PersonDetails person;
	
	@GetMapping("/getPersonDetail")
	public PersonDetails getPersonDetail(){
		return person;
	}

}
