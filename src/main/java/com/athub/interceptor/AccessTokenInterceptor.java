package com.athub.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.athub.job.AccessTokenJob;
import com.athub.utils.RequestHeaderContext;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author Wang wenjun
 */
@Component
public class AccessTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        PrintWriter out;

        // 对于注解的判断
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 如果自己拥有NoNeedToken标注或者所属的class拥有NoNeedToken 就直接放行
        if (handlerMethod.getMethodAnnotation(NoNeedAccessToken.class) != null || handlerMethod.getBeanType().isAnnotationPresent(NoNeedAccessToken.class)) {
            return true;
        }

        String accessToken = AccessTokenJob.getAccessTokenStr();
//        String accessToken = request.getHeader("accessToken");

        if (accessToken == null || !AccessTokenJob.getAccessTokenStr().equals(accessToken)) {
            JSONObject res = new JSONObject();
            res.put("code", "40001");
            res.put("msg", "accessToken is null or wrong");
            out = response.getWriter();
            out.append(res.toString());
            return false;
        }
        new RequestHeaderContext.RequestHeaderContextBuild().accessToken(accessToken).bulid();
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

}
