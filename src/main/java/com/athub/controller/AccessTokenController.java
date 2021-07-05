package com.athub.controller;

import com.athub.dto.Result;
import com.athub.job.AccessTokenJob;
import com.athub.utils.ResultUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Wang wenjun
 */
@CrossOrigin("*")
@RestController
public class AccessTokenController {

    @GetMapping("/accessToken")
    public Result getAccessTokenStr() {
        return ResultUtils.success(AccessTokenJob.getAccessTokenStr(), "");
    }

}
