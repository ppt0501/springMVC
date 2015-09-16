package com.ppt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ppt.platform.dto.PersonDTO;

@RestController
@RequestMapping("/person")
public class PersonController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public PersonDTO findPerson() {
		System.out.println("find person");
		return new PersonDTO("ppt",12,PersonDTO.Sex.MALE);
	}
	
}
