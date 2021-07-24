package com.athub.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.athub.dto.*;
import com.athub.service.MenuService;
import com.athub.utils.RequestHeaderContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Wang wenjun
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${gzhInfo.menuCreateUrlFormat}")
    private String menuCreateUrlFormat;

    @Value("${gzhInfo.menuGetUrlFormat}")
    private String menuGetUrlFormat;

    @Value("${gzhInfo.menuDeleteUrlFormat}")
    private String menuDeleteUrlFormat;

    @Override
    public int createMenu() {
        String accessToken = RequestHeaderContext.getInstance().getAccessToken();
        String url = String.format(menuCreateUrlFormat, accessToken);
        Menu menu = this.createMenuOper();
        String menuJson = restTemplate.postForObject(url, menu, String.class);
        JSONObject jsonObject = JSONObject.parseObject(menuJson);
        if (0 != jsonObject.getIntValue("errcode")) {
            return jsonObject.getIntValue("errcode");
        } else {
            return 0;
        }
    }

    @Override
    public int deleteMenu() {
        String accessToken = RequestHeaderContext.getInstance().getAccessToken();
        String url = String.format(menuDeleteUrlFormat, accessToken);
        String menuJson = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(menuJson);
        if (0 != jsonObject.getIntValue("errcode")) {
            return jsonObject.getIntValue("errcode");
        } else {
            return 0;
        }
    }

    @Override
    public JSONObject getMenu() {
        String accessToken = RequestHeaderContext.getInstance().getAccessToken();
        String url = String.format(menuGetUrlFormat, accessToken);
        String menuJson = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(menuJson);
        return jsonObject;
    }

    private Menu createMenuOper() {
        // 子按钮（菜单）
        ClickButton btn11 = new ClickButton();
        btn11.setName("开发工具");
        btn11.setType("click");
        btn11.setKey("11");

        ViewButton btn12 = new ViewButton();
        btn12.setName("资源合集");
        btn12.setType("view");
        btn12.setUrl("https://www.baidu.com/");

        ViewButton btn21 = new ViewButton();
        btn21.setName("知乎");
        btn21.setType("view");
        btn21.setUrl("https://www.zhihu.com/people/zqtao23/activities");

        ViewButton btn22 = new ViewButton();
        btn22.setName("简书");
        btn22.setType("view");
        btn22.setUrl("https://www.jianshu.com/u/7110a2ba6f9e");

        ViewButton btn31 = new ViewButton();
        btn31.setName("资源屋");
        btn31.setType("view");
        btn31.setUrl("http://www.baidu.com");

        ViewButton btn32 = new ViewButton();
        btn32.setName("Github");
        btn32.setType("view");
        btn32.setUrl("https://github.com/zqtao2332");

        ViewButton btn33 = new ViewButton();
        btn33.setName("博客");
        btn33.setType("view");
        btn33.setUrl("http://www.zqtaotao.cn");

        // 一级菜单
        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("开发助手");
        mainBtn1.setSub_button(new BasicButton[]{btn11, btn12});

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("知识驿站");
        mainBtn2.setSub_button(new BasicButton[]{btn21, btn22});

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("更多体验");
        mainBtn3.setSub_button(new BasicButton[]{btn31, btn32, btn33});

        Menu menu = new Menu();
        menu.setButton(new BasicButton[]{mainBtn1, mainBtn2, mainBtn3});
        return menu;
    }

}
