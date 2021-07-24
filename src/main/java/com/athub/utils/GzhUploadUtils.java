package com.athub.utils;

import com.athub.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class GzhUploadUtils {

    private final static Logger logger = LoggerFactory.getLogger(GzhUploadUtils.class);

    private static RestTemplate restTemplate = SpringContextHelper.getBean(RestTemplate.class);

    public static ResponseEntity uploadFile(String url, File file, String type) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
            httpHeaders.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            FileSystemResource resource = new FileSystemResource(file);
            param.add("media", resource);
            HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(param, httpHeaders);
            ResponseEntity responseEntity = restTemplate.postForEntity(url, formEntity, String.class);
            return responseEntity;
        } catch (Exception e) {
            logger.error("文件上传失败:" + e.toString());
            throw new BusinessException("500", "文件上传失败");
        }
    }

}
