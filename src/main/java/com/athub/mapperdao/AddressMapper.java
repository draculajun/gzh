package com.athub.mapperdao;

import com.athub.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Author Wang wenjun
 */
public interface AddressMapper extends BaseMapper<Address> {

    List<Address> selectList(Address address);

}
