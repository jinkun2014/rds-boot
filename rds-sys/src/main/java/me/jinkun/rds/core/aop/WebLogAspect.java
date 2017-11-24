package me.jinkun.rds.core.aop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.jinkun.rds.core.utils.UtilDate;
import me.jinkun.rds.sys.service.SysLogService;
import me.jinkun.rds.sys.web.form.SysLogForm;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 访问日志控制
 */
@Aspect
@Component
public class WebLogAspect {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    SysLogService sysLogService;

    @Pointcut("execution(public * me.jinkun.rds..*Controller.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        //拦截的实体类
        Object target = joinPoint.getTarget();

        Api api = target.getClass().getAnnotation(Api.class);

        //拦截的方法名称
        String methodName = joinPoint.getSignature().getName();

        //拦截的方法参数
        Object[] args = joinPoint.getArgs();

        //拦截的参数类型
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();

        Method method = target.getClass().getMethod(methodName, parameterTypes);

        ApiOperation operation = method.getAnnotation(ApiOperation.class);

        //保存日志，这里可以换redis或mongdb等其它方式
        SysLogForm log = new SysLogForm();
        log.setIp(request.getRemoteAddr());
        log.setLoginName("admin");
        log.setTime(UtilDate.formDate(new Date(), UtilDate.yyyy_MM_dd_HH_mm_ss));
        log.setUrl(request.getRequestURL().toString());
        log.setClazz(joinPoint.getSignature().getDeclaringTypeName());
        log.setMethod(joinPoint.getSignature().getName());
        if (api != null) {
            log.setMoudle(api.description());
        }
        if (operation != null) {
            log.setFunction(operation.value());
        }
        //sysLogService.add(log);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        //logger.info("RESPONSE : " + ret);
    }
}