package com.athub.controller;

import com.athub.service.CoreService;
import com.athub.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/hello")
public class DemoController {

    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);


    @GetMapping
    public String say1(String signature, String timestamp, String nonce, String echostr) {

        System.out.println(("signature:" + signature));
        System.out.println(("timestamp:" + timestamp));
        System.out.println(("nonce:" + nonce));
        System.out.println(("echostr:" + echostr));

        return TokenUtils.check(signature, timestamp, nonce, echostr);
    }

    @GetMapping("/say2")
    public String say2(String a) {
        logger.info(a);
        return a;
    }

    @Autowired
    private CoreService coreService;

    @PostMapping
    public void post(HttpServletRequest request, HttpServletResponse response) {
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
