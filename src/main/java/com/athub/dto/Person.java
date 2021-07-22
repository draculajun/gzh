package com.athub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Person implements Serializable {

    private Integer personuid;

    private Integer[] personuids;

    private String personid;

    private String siteid;

    private String orgid;

    private String realname;

    private String email;

    private String phone;

    private Integer department;

    private Integer title;

    private String supervisor;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date hiredate;


    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date officialdate;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date terminationdate;

    private String jobcode;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date lastevaldate;

    private Integer starlevel;

    private String status;

    private String validation;

    private String enterby;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enterdate;

    private String changeby;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date changedate;


}
