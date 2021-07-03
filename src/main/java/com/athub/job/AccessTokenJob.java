package com.athub.job;

import com.alibaba.fastjson.JSONObject;
import com.athub.dto.AccessToken;
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

    private static String accessTokenStr;

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 2)
    public void setAccessToken() {
        String accessTokenUrlFormat = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        String accessTokenUrl = String.format(accessTokenUrlFormat, appId, appSecret);
        String accessTokenJson = restTemplate.getForObject(accessTokenUrl, String.class);
        AccessToken accessToken = JSONObject.parseObject(accessTokenJson, AccessToken.class);
        accessTokenStr = accessToken.getAccessToken();
    }

    public static String getAccessTokenStr() {
        return accessTokenStr;
    }

}
