package com.athub.controller;

import com.athub.dto.Result;
import com.athub.entity.Person;
import com.athub.service.PersonService;
import com.athub.utils.AthubBaseController;
import com.athub.utils.ResultUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/person")
public class PersonController extends AthubBaseController<Person, PersonService> {

}
