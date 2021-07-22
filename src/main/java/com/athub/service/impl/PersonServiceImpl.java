package com.athub.service.impl;

import com.athub.dto.Person;
import com.athub.mapperdao.PersonMapper;
import com.athub.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public Person get(Long id) {
        return personMapper.get(id);
    }

}
