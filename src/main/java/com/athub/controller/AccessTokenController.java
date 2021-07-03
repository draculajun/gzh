package com.athub.controller;

import com.athub.dto.Result;
import com.athub.job.AccessTokenJob;
import com.athub.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Wang wenjun
 */
@RestController("/accessToken")
public class AccessTokenController {

    @GetMapping
    public Result getAccessTokenStr() {
        return ResultUtils.success(AccessTokenJob.getAccessTokenStr());
    }

}
