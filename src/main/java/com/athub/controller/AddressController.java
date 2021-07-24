package com.athub.controller;

import com.athub.entity.Address;
import com.athub.dto.Result;
import com.athub.service.AddressService;
import com.athub.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Long id) throws IOException {
        return ResultUtils.success(addressService.selectById(id), "");
    }

    @PostMapping
    public Result insert(@RequestBody Address address) {
        return ResultUtils.success(addressService.insert(address), "");
    }

    @PostMapping("/list")
    public Result list(@RequestBody Address address) {
        return ResultUtils.success(addressService.selectList(address), "");
    }
}
