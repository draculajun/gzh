package com.athub.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wang wenjun
 * 全局异常捕捉
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Map<String, Object> exceptionHandler(BusinessException exception) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", exception.getExceptionCode());
        map.put("message", exception.getExceptionMessage());
        return map;
    }

}
