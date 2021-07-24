package com.athub.service.impl;

import com.athub.dto.resp.*;
import com.athub.service.CoreService;
import com.athub.utils.MessageUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

//            textMessage.setFuncFlag(0);
            if (msgType.equals("text")) {               // 文本消息
                //回复文本消息
//                TextMessageResp textMessage = new TextMessageResp();
//                textMessage = (TextMessageResp) this.getBaseResponse(textMessage, fromUserName, toUserName);
//                textMessage.setMsgType("text");
//                textMessage.setContent("好勒！！");
//                respMessage = MessageUtils.textMessageToXml(textMessage);

                //回复图片消息
                ImageMessageResp imgMessage = new ImageMessageResp();
                imgMessage = (ImageMessageResp) this.getBaseResponse(imgMessage, fromUserName, toUserName);
                imgMessage = this.getImageResponse(imgMessage);
                respMessage = MessageUtils.imageMessageToXml(imgMessage);

                //回复图文消息
//                NewsMessageResp newMessage = new NewsMessageResp();
//                newMessage = (NewsMessageResp) this.getBaseResponse(newMessage, fromUserName, toUserName);
//                newMessage = this.getNewsResponse(newMessage);
//                respMessage = MessageUtils.newsMessageToXml(newMessage);
            } else if (msgType.equals("event")) {                   //订阅事件
                TextMessageResp textMessage = new TextMessageResp();
                textMessage = (TextMessageResp) this.getBaseResponse(textMessage, fromUserName, toUserName);
                textMessage.setMsgType("text");

                String eventType = requestMap.get("Event");
                if (eventType.equalsIgnoreCase("subscribe")) {
                    textMessage.setContent("今天吃汉拿山吗牛阿姨？");
                    respMessage = MessageUtils.textMessageToXml(textMessage);
                } else if (eventType.equalsIgnoreCase("unsubscribe")) {
                    textMessage.setContent("8,good luck guy！");
                    respMessage = MessageUtils.textMessageToXml(textMessage);
                } else if (eventType.equals("LOCATION")) {

                } else if (eventType.equals("CLICK")) {
                    String eventKey = requestMap.get("EventKey");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }

    private BaseMessageResp getBaseResponse(BaseMessageResp baseMessageResp, String fromUserName, String toUserName) {
        baseMessageResp.setFromUserName(toUserName);
        baseMessageResp.setToUserName(fromUserName);
        baseMessageResp.setCreateTime(new Date().getTime());
        return baseMessageResp;
    }

    //回复图片消息
    private ImageMessageResp getImageResponse(ImageMessageResp imgMessage) {
        imgMessage.setMsgType("image");
        Image img = new Image();
        img.setMediaId("Bn5Yiqz-DPo0Dmrmdygr3gwO1RmCj7gHQo4YZmJpzuk");
        imgMessage.setImage(img);
        return imgMessage;
    }

    //回复图文消息
    private NewsMessageResp getNewsResponse(NewsMessageResp newMessage) {
        newMessage.setMsgType("news");
        Article article = new Article();
        article.setUrl("http://mp.weixin.qq.com/s?__biz=Mzg2MzY0OTQ3Mw==&mid=100000005&idx=1&sn=ddc9452fcbb23a4beef2b83f330d54cb&chksm=4e742aab7903a3bd7c8e31000d2b6d1cb34d120b0f16dbe0e368c04ea18fea8eda9788a26edb#rd");
        article.setDescription("DESCRIPTION");
        article.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/7DafJS7GQmsTwAvQgqIjtMaKQQpNrWyPK6oH0rH1t86libVMRT1Kmv1po6nRsVB0Gd4StgOEiaBp6aPnANNBEIibw/0?wx_fmt=jpeg");
        article.setTitle("TITLE");
        List<Article> articleList = new ArrayList();
        articleList.add(article);
        newMessage.setArticles(articleList);
        newMessage.setArticleCount(1);
        return newMessage;
    }


}
