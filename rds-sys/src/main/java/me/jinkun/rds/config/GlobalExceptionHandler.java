package me.jinkun.rds.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import me.jinkun.rds.core.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //TODO 不知道怎么设置Bean的初始化顺序
    @Autowired
    private FastJsonConfig fastJsonConfig;

    private String errorViewName = "/error";

    @ExceptionHandler(BaseException.class)
    public ModelAndView exceptionHandler(RuntimeException e, HttpServletRequest request, HttpServletResponse response) {
        boolean isRestful = true;
        String accept = request.getHeader("accept");
        if (!StringUtils.isEmpty(accept) && accept.contains("text/html")) {
            isRestful = false;
        }
        BaseException base = (BaseException) e;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("resultCode", base.getEnumValue());
        modelAndView.addObject("message", base.getMessage());
        if (isRestful) {
            FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();
            fastJsonJsonView.setFastJsonConfig(fastJsonConfig);
            modelAndView.setView(fastJsonJsonView);
        } else {
            modelAndView.setViewName(errorViewName);
        }
        return modelAndView;
    }
}