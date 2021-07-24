package com.athub.service.impl;

import com.athub.entity.Address;
import com.athub.mapperdao.AddressMapper;
import com.athub.service.AddressService;
import com.athub.utils.service.impl.AthubBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author Wang wenjun
 */
@Service
public class AddressServiceImpl extends AthubBaseServiceImpl<Address, AddressMapper> implements AddressService {

}
