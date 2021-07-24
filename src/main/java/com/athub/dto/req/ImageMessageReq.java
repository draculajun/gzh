package com.athub.dto.req;

/**
 * @Author Wang wenjun
 */
public class ImageMessageReq extends BaseMessageReq {

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    private String picUrl;

}
