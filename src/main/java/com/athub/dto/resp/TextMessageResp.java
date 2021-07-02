package com.athub.dto.resp;

/**
 * @Author Wang wenjun
 */
public class TextMessageResp extends BaseMessageResp {

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    /**
     * 回复的消息内容
     */
    private String Content;

}
