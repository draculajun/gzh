package com.athub.service.impl;

import com.athub.entity.Address;
import com.athub.mapperdao.AddressMapper;
import com.athub.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Wang wenjun
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Address selectById(Long id) {
        return addressMapper.selectById(id);
    }

    @Override
    public int insert(Address address) {
        return addressMapper.insert(address);
    }

    @Override
    public List<Address> selectList(Address address) {
        return addressMapper.selectList(address);
    }
}
