package com.athub.controller;

import com.athub.entity.Address;
import com.athub.dto.Result;
import com.athub.service.AddressService;
import com.athub.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Wang wenjun
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/address")
public class AddressController extends AthubBaseController<Address, AddressService> {

}
