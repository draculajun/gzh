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
@TableName("address")
public class Address implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField(exist = false)
    private List<Long> ids;

    @TableField("person_id")
    private Long personId;

    private String description;

    private String enterby;

    @TableField("enterdate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enterDate;

    @TableField(exist = false)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @TableField(exist = false)
    private Person person;

    @TableLogic
    private boolean deleted;

}
