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

            TextMessageResp textMessage = new TextMessageResp();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType("text");
//            textMessage.setFuncFlag(0);
            if (msgType.equals("text")) {               // 文本消息
                String content = requestMap.get("Content");
                textMessage.setContent("哦，早点肥来哦");
                respMessage = MessageUtils.textMessageToXml(textMessage);
            } else if (msgType.equals("event")) {                   //订阅事件
                String eventType = requestMap.get("Event");
                if (eventType.equalsIgnoreCase("subscribe")) {
                    textMessage.setContent("今天加班吗牛阿姨？");
                    respMessage = MessageUtils.textMessageToXml(textMessage);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

}
