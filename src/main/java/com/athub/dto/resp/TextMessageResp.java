package com.athub.dto.resp;

import lombok.Data;

/**
 * @Author Wang wenjun
 */
@Data
public class TextMessageResp extends BaseMessageResp {

    /**
     * 回复的消息内容
     */
    private String Content;

}
