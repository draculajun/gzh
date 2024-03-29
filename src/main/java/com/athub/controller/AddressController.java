package com.athub.controller;

import com.athub.entity.Address;
import com.athub.interceptor.NoNeedAccessToken;
import com.athub.service.AddressService;
import com.athub.utils.AthubBaseController;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Wang wenjun
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/address")
@NoNeedAccessToken
public class AddressController extends AthubBaseController<Address, AddressService> {

}
