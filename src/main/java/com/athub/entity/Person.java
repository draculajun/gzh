package com.athub.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Wang wenjun
 */
@Data
@TableName("person")
public class Person extends Page implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(exist = false)
    private List<Long> ids;

    private String name;

    private Integer age;

    private String enterby;

    @TableField("enterdate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enterDate;

    @TableLogic
    private Integer deleted;

}
