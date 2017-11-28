package me.jinkun.rds.config;

import me.jinkun.rds.core.support.web.ResultCode;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 用来处理系统异常
 */
@Controller
public class HttpErrorHandlerController extends AbstractErrorController {
    public static final String ERROR_PATH = "/error";
    public static String ERROR_VIEW = "/error";

    public HttpErrorHandlerController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return this.ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(request, false));
        response.setStatus(status.value());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("resultCode", ResultCode.SYSTEM_ERROR);
        modelAndView.addObject("message", errorMessage(status.value()));
        return modelAndView;
    }

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Object error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, false);
        HttpStatus status = getStatus(request);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultCode", ResultCode.SYSTEM_ERROR);
        resultMap.put("message", errorMessage(status.value()));
        return resultMap;
    }

    private String errorMessage(int status) {
        String msg = "";
        switch (status) {
            case 403:
            case 404:
                msg = "访问链接不存在";
                break;
            case 500:
            default:
                msg = "服务器内部错误";
        }
        return msg;
    }
}