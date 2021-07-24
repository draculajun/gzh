package com.athub.utils;

import com.athub.dto.Result;
import com.athub.exception.BusinessException;
import com.athub.utils.service.AthubBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
public abstract class AthubBaseController<T, Service extends AthubBaseService<T>> {

    @Autowired
    protected Service baseService;

    @GetMapping("/{id}")
    public Result selectOne(@PathVariable("id") Integer id) {
        T t = (T) baseService.selectOne(id);
        return ResultUtils.success(t, "");
    }

    @PostMapping
    public Result insert(@RequestBody T t) {
        T t1;
        try {
            t1 = baseService.insert(t);
        } catch (BusinessException e) {
            return ResultUtils.error(500, e.getMessage());
        }
        return ResultUtils.success(t1, "新增成功");
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") Integer id, @RequestBody T t) {
        try {
            baseService.update(t);
        } catch (BusinessException e) {
            return ResultUtils.error(500, e.getMessage());
        }
        return ResultUtils.success(null, "更新成功");
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        try {
            baseService.delete(id);
        } catch (BusinessException e) {
            return ResultUtils.error(500, e.getMessage());
        }
        return ResultUtils.success("删除成功");
    }

    @PostMapping("/list")
    public Result list(@RequestBody T t) {
        return ResultUtils.success(baseService.selectList(t), "LIST获取成功");
    }

    @PostMapping(value = "/page")
    public Result page(@RequestBody T t) {
        return ResultUtils.success(baseService.page(t), "PAGE获取成功");
    }
}
