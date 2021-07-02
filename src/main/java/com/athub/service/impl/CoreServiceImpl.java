package com.athub.service.impl;

import com.athub.dto.resp.TextMessageResp;
import com.athub.service.CoreService;
import com.athub.utils.MessageUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @Author Wang wenjun
 */
@Service
public class CoreServiceImpl implements CoreService {

    @Override
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // xml请求解析
            Map<String, String> requestMap = MessageUtils.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            System.out.println("msgType:" + msgType);

            TextMessageResp textMessage = new TextMessageResp();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType("text");
//            textMessage.setFuncFlag(0);
            // 文本消息
            if (msgType.equals("text")) {
                // 接收用户发送的文本消息内容
                String content = requestMap.get("Content");

                if ("1".equals(content)) {
                    textMessage.setContent("1是很好的");
                    // 将文本消息对象转换成xml字符串
                    respMessage = MessageUtils.textMessageToXml(textMessage);
                } else if ("2".equals(content)) {
                    textMessage.setContent("我不是2货");
                    // 将文本消息对象转换成xml字符串
                    respMessage = MessageUtils.textMessageToXml(textMessage);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

}
