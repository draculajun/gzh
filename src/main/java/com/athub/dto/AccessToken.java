package com.athub.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Author Wang wenjun
 */
@Data
public class AccessToken {

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private int expiresin;

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresin=" + expiresin +
                '}';
    }
}
