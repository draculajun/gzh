package com.athub.dto;

import lombok.Data;

/**
 * @Author Wang wenjun
 * @description: view类型的按钮(有type 、 name 、 url三个属性)
 */
@Data
public class ViewButton extends BasicButton {

    /**
     * 网页链接，用户点击菜单可打开链接，不超过1024字节。
     * type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     */
    private String url;

}
