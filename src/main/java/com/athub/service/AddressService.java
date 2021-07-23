package com.athub.service;

import com.athub.entity.Address;

import java.util.List;

/**
 * @Author Wang wenjun
 */
public interface AddressService {

    Address selectById(Long id);

    int insert(Address address);

    List<Address> selectList(Address address);

}
