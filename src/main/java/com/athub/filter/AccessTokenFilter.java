package com.athub.filter;

import com.alibaba.fastjson.JSONObject;
import com.athub.job.AccessTokenJob;
import com.athub.utils.RequestHeaderContext;
import com.athub.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Wang wenjun
 */

@Configuration
@WebFilter
public class AccessTokenFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenFilter.class);

    public String[] getWhiteArray() {
        String[] whiteArray = new String[]{
                "/accessToken",
                "/gzh"
        };
        return whiteArray;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String[] whiteArray = this.getWhiteArray();
            for (int i = 0; i < whiteArray.length; i++) {
                if (httpServletRequest.getRequestURI().indexOf(whiteArray[i]) > -1) {
                    filterChain.doFilter(httpServletRequest, httpServletResponse);
                    return;
                }
            }

            boolean flag = false;
            String accessToken = httpServletRequest.getHeader("accessToken");
            if (accessToken == null || "".equals(accessToken)) {
                logger.error("AccessToken为空!");
                flag = false;
            }
            if (accessToken.equals(AccessTokenJob.getAccessTokenStr())) {
                flag = true;
            }
            if (!flag) {
                this.responseOutWithJson(httpServletResponse, ResultUtils.error(40001, "AccessToken验证失败!"));
            } else {
                new RequestHeaderContext.RequestHeaderContextBuild().accessToken(accessToken).bulid();
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        } finally {
            RequestHeaderContext.clean();
        }
    }

    /**
     * 以JSON格式输出
     */
    protected void responseOutWithJson(HttpServletResponse response,
                                       Object responseObject) {
        JSONObject responseJSONObject = (JSONObject) JSONObject.toJSON(responseObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
