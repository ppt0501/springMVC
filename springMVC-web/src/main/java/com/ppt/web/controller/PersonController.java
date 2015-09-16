package com.ppt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ppt.platform.dto.PersonDTO;
import com.ppt.platform.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personSerivce;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public PersonDTO test() {
		PersonDTO dto = personSerivce.getPerson();
		System.out.println(dto.toString());
		return new PersonDTO("ppt",12,PersonDTO.Sex.MALE);
	}
}
