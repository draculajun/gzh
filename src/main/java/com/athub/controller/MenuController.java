package com.athub.controller;

import com.alibaba.fastjson.JSONObject;
import com.athub.dto.Result;
import com.athub.exception.BusinessException;
import com.athub.service.MenuService;
import com.athub.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Wang wenjun
 */
@CrossOrigin("*")
@RestController("/menuOper")
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping
    public Result createMenu() {
        int code = menuService.createMenu();
        if (code != 0) {
            throw new BusinessException(String.valueOf(code), "创建菜单失败！");
        }
        return ResultUtils.success("");
    }

    @DeleteMapping
    public Result deleteMenu() {
        int code = menuService.deleteMenu();
        if (code != 0) {
            throw new BusinessException(String.valueOf(code), "删除菜单失败！");
        }
        return ResultUtils.success("");
    }

    @GetMapping
    public Result getMenu() {
        JSONObject jsonObject = menuService.getMenu();
        if (jsonObject == null) {
            throw new BusinessException("500", "获取菜单失败！");
        }
        return ResultUtils.success("");
    }

}
