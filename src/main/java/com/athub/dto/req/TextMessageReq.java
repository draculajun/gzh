package com.athub.dto.req;

/**
 * @Author Wang wenjun
 */
public class TextMessageReq extends BaseMessageReq {

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    private String Content;

}
