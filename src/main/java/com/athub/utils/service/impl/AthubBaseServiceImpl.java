package com.athub.utils.service.impl;

import com.athub.entity.Page;
import com.athub.utils.AthubBaseMapper;
import com.athub.utils.service.AthubBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wang wenjun
 */
public class AthubBaseServiceImpl<T extends Page, M extends AthubBaseMapper<T>> extends ServiceImpl implements AthubBaseService<T> {

    @Autowired
    protected M mapper;

    @Override
    public T insert(T t) {
        mapper.insert(t);
        return t;
    }

    @Override
    public void update(T t) {
        mapper.updateById(t);
    }

    @Override
    public T selectOne(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public void delete(Integer id) {
        mapper.deleteById(id);
    }

    @Override
    public Integer selectPageCount(T t) {
        return mapper.selectPageCount(t);
    }

    @Override
    public List<T> selectPageList(T t) {
        return mapper.selectPageList(t);
    }

    @Override
    public Page page(T t) {
        t.setOffset();
        int num = this.selectPageCount(t);
        List<T> list = new ArrayList<T>();
        if (num > 0) {
            list = this.selectPageList(t);
        }
        Page page = new Page();
        page.setTotal(num);
        page.setPageData(list);
        return page;
    }

    @Override
    public List<T> selectList(T t) {
        return mapper.selectList(t);
    }

    @Override
    public void batchInsert(List<T> t) {
        mapper.batchInsert(t);
    }

    @Override
    public void batchUpdate(List<T> t) {
        mapper.batchUpdate(t);
    }

}
