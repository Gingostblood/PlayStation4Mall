package com.gingost.layui.service.impl;


import cn.hutool.json.JSONObject;
import com.gingost.layui.annotation.Log;
import com.gingost.layui.repository.LogJpa;
import com.gingost.layui.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * @author Lezzy
 * @description
 * @date 2020/4/27 22:17
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogJpa logJpa;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String username, String ip, String source, ProceedingJoinPoint joinPoint, com.gingost.layui.domain.system.Log log) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log aopLog=method.getAnnotation(Log.class);
        //获取方法路径
        String methodName=joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";
        StringBuilder params=new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        //描述
        if(log!=null){
            log.setDescription(aopLog.value());
        }
        assert log!=null;
        log.setRequestIp(ip);

        String loginPath = "login";
        if(loginPath.equals(signature.getName())){
            try {
                assert argValues != null;
                username = new JSONObject(argValues[0]).get("username").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        log.setMethod(methodName);
        log.setSource(source);
        log.setUsername(username);
        log.setParams(params.toString() + " }");
        logJpa.save(log);
    }
}
