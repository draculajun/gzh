package com.athub.service.impl;

import com.athub.entity.Person;
import com.athub.mapperdao.PersonMapper;
import com.athub.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public Person selectById(Long id) {
        return personMapper.selectById(id);
    }

    @Override
    public int insert(Person person) {
        return personMapper.insert(person);
    }

    @Override
    public int deleteById(Long id) {
        return personMapper.deleteById(id);
    }

}
