package com.athub.job;

import com.alibaba.fastjson.JSONObject;
import com.athub.dto.AccessToken;
import com.athub.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Wang wenjun
 */
@Component
public class AccessTokenJob {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${gzhInfo.appId}")
    private String appId;

    @Value("${gzhInfo.appSecret}")
    private String appSecret;

    @Value("${gzhInfo.accessTokenUrlFormat}")
    private String accessTokenUrlFormat;

    private static String accessTokenStr;

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 2)
    public void setAccessToken() {
        String accessTokenUrl = String.format(accessTokenUrlFormat, appId, appSecret);
        String accessTokenJson = restTemplate.getForObject(accessTokenUrl, String.class);
        AccessToken accessToken = JSONObject.parseObject(accessTokenJson, AccessToken.class);
        accessTokenStr = accessToken.getAccessToken();
    }

    public static String getAccessTokenStr() {
        if (accessTokenStr == null) {
            throw new BusinessException("500", "获取accessToken失败！");
        }
        return accessTokenStr;
    }

}
