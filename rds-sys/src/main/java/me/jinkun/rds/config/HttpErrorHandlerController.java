package me.jinkun.rds.config;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import me.jinkun.rds.core.exception.BaseException;
import me.jinkun.rds.core.support.web.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用来处理系统异常
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class HttpErrorHandlerController extends AbstractErrorController {

    private static final Logger log = LoggerFactory.getLogger(HttpErrorHandlerController.class);

    @Value("${server.error.path:${error.path:/error}}")
    private static String errorPath = "/error";

    public HttpErrorHandlerController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return this.errorPath;
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        response.setStatus(status.value());
        if (status == HttpStatus.NOT_FOUND) {
            return handleHtmlError(ResultCode.SYSTEM_ERROR, "资源未找到");
        }
        return handleHtmlError(ResultCode.SYSTEM_ERROR, "服务器内部错误");
    }

    @RequestMapping
    @ResponseBody
    public Object errorJson(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        response.setStatus(status.value());
        if (status == HttpStatus.NOT_FOUND) {
            return handleJSONError(ResultCode.SYSTEM_ERROR, "资源未找到");
        }
        return handleJSONError(ResultCode.SYSTEM_ERROR, "服务器内部错误");
    }

    protected ModelAndView handleJSONError(ResultCode resultCode, String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resultCode", resultCode);
        modelAndView.addObject("message", message);
        modelAndView.setView(new FastJsonJsonView());
        return modelAndView;
    }

    protected ModelAndView handleHtmlError(ResultCode resultCode, String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resultCode", resultCode);
        modelAndView.addObject("message", message);
        modelAndView.setViewName(errorPath);
        return modelAndView;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView exceptionHandler(RuntimeException e, HttpServletRequest request, HttpServletResponse response) {
        boolean isRestful = true;
        String accept = request.getHeader("accept");
        if (!StringUtils.isEmpty(accept) && accept.contains("text/html")) {
            isRestful = false;
        }
        if (isRestful) {
            if (e instanceof BaseException) {
                BaseException base = (BaseException) e;
                return handleJSONError((ResultCode) base.getEnumValue(), base.getMessage());
            }
            return handleJSONError(ResultCode.SYSTEM_ERROR, "未知错误");
        } else {
            if (e instanceof BaseException) {
                BaseException base = (BaseException) e;
                return handleHtmlError((ResultCode) base.getEnumValue(), base.getMessage());
            }
            return handleHtmlError(ResultCode.SYSTEM_ERROR, "未知错误");
        }
    }
}