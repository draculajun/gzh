package com.athub.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author Wang wenjun
 */
public interface MenuService {
    
    int createMenu();

    int deleteMenu();

    JSONObject getMenu();

}
