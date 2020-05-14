package com.gingost.layui.untils;

import com.gingost.layui.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Lezzy
 * @description
 * @data 2020/4/2722:29
 **/
public class SecurityUtils {
    public static User getLoginUser(){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
