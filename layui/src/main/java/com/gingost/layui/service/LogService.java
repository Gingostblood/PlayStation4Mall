package com.gingost.layui.service;



import com.gingost.layui.domain.system.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Lezzy
 * @description
 * @date 2020/4/27 22:17
 */
public interface LogService {

    @Async
    void save(String username, String ip, String source, ProceedingJoinPoint joinPoint, Log log);
}
