package com.athub.utils;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface AthubBaseMapper<T> extends BaseMapper<T> {

    void batchInsert(List<T> t);

    void batchUpdate(List<T> t);

    List<T> selectList(T t);

    Integer selectPageCount(T t);

    List<T> selectPageList(T t);

}
