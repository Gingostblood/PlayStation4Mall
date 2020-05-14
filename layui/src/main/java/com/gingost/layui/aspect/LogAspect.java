package com.gingost.layui.aspect;

import com.gingost.layui.domain.system.Log;
import com.gingost.layui.service.LogService;

import com.gingost.layui.untils.HttpUtils;
import com.gingost.layui.untils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;


@Component
@Slf4j
@Aspect
public class LogAspect {
    private final LogService logService;

    ThreadLocal<Long> currentTime=new ThreadLocal<>();

    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    /**
     * 切入点配置
     */
    @Pointcut("@annotation(com.gingost.layui.annotation.Log)")
    public void  logPointcut(){}

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result;
        currentTime.set(System.currentTimeMillis());
        result=joinPoint.proceed();
        Log log=new Log("INFO",System.currentTimeMillis()-currentTime.get());
        currentTime.remove();
        HttpServletRequest request= getHttpServletRequest();
        String source=request.getHeader("source");
        if(source==null||source.equals("")){
            source="PC";
        }
        logService.save(getUsername(), HttpUtils.getIp(request),source,joinPoint,log);
        return result;
    }

    /**
     *配置异常通知
     */
    @AfterThrowing(pointcut = "logPointcut()",throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint,Throwable e){
        Log log=new Log("ERROR",System.currentTimeMillis()-currentTime.get());
        currentTime.remove();
        log.setExceptionDetail(getStackTrace(e).getBytes());
        HttpServletRequest request= getHttpServletRequest();
        String source=request.getHeader("source");
        if(source==null||source.equals("")){
            source="PC";
        }
        logService.save(getUsername(), HttpUtils.getIp(request),source,(ProceedingJoinPoint)joinPoint,log);
    }

    private String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    private String getUsername() {
        return SecurityUtils.getLoginUser().getUsername();
    }

    private HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
