package com.athub.mapperdao;

import com.athub.dto.Person;
import org.apache.ibatis.annotations.Param;

public interface PersonMapper {

    Person get(@Param("id") Long id);

}
