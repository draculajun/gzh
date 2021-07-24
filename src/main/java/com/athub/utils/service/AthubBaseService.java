package com.athub.utils.service;

import com.athub.entity.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author Wang wenjun
 */
public interface AthubBaseService<T> extends IService {

    T insert(T t);

    void update(T t);

    T selectOne(Integer id);

    void delete(Integer id);

    Integer selectPageCount(T t);

    List<T> selectPageList(T t);

    Page page(T t);

    List<T> selectList(T t);

    void batchInsert(List<T> t);

    void batchUpdate(List<T> t);

}
