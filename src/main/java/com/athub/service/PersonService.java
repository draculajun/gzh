package com.athub.service;

import com.athub.entity.Person;

public interface PersonService {

    Person selectById(Long id);

    int insert(Person person);

    int deleteById(Long id);

}
