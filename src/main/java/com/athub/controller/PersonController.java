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

    @PostMapping("/list")
    public Result list(@RequestBody Person person) {
        return ResultUtils.success(baseService.selectList(person), "");
    }

    @PostMapping("/page")
    public Result page(@RequestBody Person person) {
        return ResultUtils.success(baseService.page(person), "");
    }

}
