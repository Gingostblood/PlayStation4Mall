package com.gingost.layui.common;

import com.gingost.layui.annotation.Log;
import org.springframework.security.access.AccessDeniedException;
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
    @Log("全局异常处理")
    //全局程序异常处理
    public ResponseEntity lezzyException(RuntimeException e) {
        return new ResponseEntity(e);
    }

//    @Log("用户登陆异常")
//    @ResponseBody
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public String lizhenyuException(UsernameNotFoundException e){
//        return "<script type=\"javascript\">alert("+e.getMessage()+")</script>";
//    }

    //网页403响应异常处理
    @Log("权限不足异常")
    @ExceptionHandler(AccessDeniedException.class)
    public String ginostException() {
        return "403";
    }
}
