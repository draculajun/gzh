package com.athub.dto;

import lombok.Data;

/**
 * @Author Wang wenjun
 * @description: 复合类型的按钮（含有子菜单的一级菜单）
 */
@Data
public class ComplexButton extends BasicButton {

    private BasicButton[] sub_button;

}
