package com.athub.dto;

import lombok.Data;

/**
 * @Author Wang wenjun
 * @description: 整个菜单对象的封装
 * 菜单对象包含多个菜单项（最多只能有3个）
 * 这些菜单项即可以是子菜单项（不含二级菜单的一级菜单），也可以是父菜单项（包含二级菜单的菜单项）
 */
@Data
public class Menu {

    private BasicButton[] button;

}
