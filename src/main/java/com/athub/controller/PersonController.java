package com.athub.controller;

import com.athub.dto.Result;
import com.athub.service.PersonService;
import com.athub.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Long id) throws IOException {
        return ResultUtils.success(personService.get(id), "");
    }


}
