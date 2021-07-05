package com.athub.controller;

import com.athub.service.CoreService;
import com.athub.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @Author Wang wenjun
 */
@CrossOrigin("*")
@RestController
public class GzhController {

    private final static Logger logger = LoggerFactory.getLogger(GzhController.class);

    @Autowired
    private CoreService coreService;

    /**
     * 修改公众号基本配置
     * 1）将token、timestamp、nonce三个参数进行字典序排序（token是公众号基本配置中设置的内容）
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @return
     */
    @GetMapping("/gzh")
    public String checkToken(String signature, String timestamp, String nonce, String echostr) {
        return TokenUtils.check(signature, timestamp, nonce, echostr);
    }

    /**
     * 根据接收内容（文本）进行自动回复
     *
     * @param request
     * @param response
     */
    @PostMapping("/gzh")
    public void answer(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
        String respMessage = coreService.processRequest(request);

        // 响应消息
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(respMessage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            out = null;
        }
    }


}
