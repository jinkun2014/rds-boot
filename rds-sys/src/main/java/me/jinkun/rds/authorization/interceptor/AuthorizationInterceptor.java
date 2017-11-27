package me.jinkun.rds.authorization.interceptor;

import com.alibaba.fastjson.JSON;
import me.jinkun.rds.authorization.annotation.Authorization;
import me.jinkun.rds.authorization.manager.token.ITokenManager;
import me.jinkun.rds.config.Constants;
import me.jinkun.rds.core.support.web.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，判断此次请求是否有权限
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private ITokenManager manager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Class<?> clazz = method.getDeclaringClass();

        //如果需要认证，但是没有登录则跳转到登录页面
        if (method.getAnnotation(Authorization.class) != null || clazz.isAnnotationPresent(Authorization.class)) {
            String requestType = request.getHeader("X-Requested-With");
            System.out.println("requestType: " + requestType); //requestType不为null时是ajax请求
            if (request.getSession().getAttribute(Constants.SESSION_USER_KEY) == null) {
                if (requestType == null) {
                    response.sendRedirect("/login.html");
                } else {
                    OutputStream os = response.getOutputStream();
                    os.write(JSON.toJSONString(ResultCode.NO_LOGIN).getBytes());
                    os.flush();
                }
                return false;
            }
            return true;
        }

//        //从header中得到token
//        String authorization = request.getHeader(Constants.AUTHORIZATION);
//        //验证token
//        TokenModel model = manager.getToken(authorization);
//        if (manager.checkToken(model)) {
//            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
//            request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
//            return true;
//        }
//        //如果验证token失败，并且方法注明了Authorization，返回401错误
//        if (method.getAnnotation(Authorization.class) != null) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
        return true;
    }
}
