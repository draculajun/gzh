package com.athub.service.impl;

import com.athub.entity.Person;
import com.athub.mapperdao.PersonMapper;
import com.athub.service.PersonService;
import com.athub.utils.service.impl.AthubBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends AthubBaseServiceImpl<Person, PersonMapper> implements PersonService {

}
