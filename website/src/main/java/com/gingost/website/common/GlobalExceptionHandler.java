package com.gingost.website.common;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:lezzy
 * @Date:2020/4/6 15:23
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    //全局程序异常处理
    public ResponseEntity lezzyException(RuntimeException e) {
        return new ResponseEntity(e);
    }

    @ResponseBody
    @ExceptionHandler(ShiroException.class)
    public ResponseEntity lezzyShiroException(ShiroException e) {
        ResponseEntity jr=new ResponseEntity();
        jr.setCode(1);
        if(e instanceof UnknownAccountException)
            jr.setMsg("账户不存在");
        else if(e instanceof LockedAccountException)
            jr.setMsg("您的账户已被封禁");
        else if(e instanceof IncorrectCredentialsException)
            jr.setMsg("密码不正确");
        else
            jr.setMsg("系统维护中...");
        e.printStackTrace();
        return jr;
    }
}
