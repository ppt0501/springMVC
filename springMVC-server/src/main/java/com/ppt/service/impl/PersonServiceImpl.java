package com.ppt.service.impl;

import org.springframework.stereotype.Service;

import com.ppt.platform.dto.PersonDTO;
import com.ppt.platform.service.PersonService;
@Service("personService")
public class PersonServiceImpl implements PersonService {

	@Override
	public PersonDTO getPerson() {
		return new PersonDTO("ppt", 27, PersonDTO.Sex.MALE);
	}

}
