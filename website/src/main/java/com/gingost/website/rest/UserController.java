package com.gingost.website.rest;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.domain.TestUser;
import com.gingost.website.domain.WebUser;
import com.gingost.website.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/515:36
 **/
@RestController
@RequestMapping("/user/")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("findOneUser")
    public ResponseEntity findOneUser(WebUser webUser){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(webUser.getUsername(),webUser.getPassword());
        subject.login(token);
        return new ResponseEntity("登陆成功");
    }

    @PostMapping("saveUser")
    public ResponseEntity saveUser(WebUser webUser){
        userService.saveUser(webUser);
        return new ResponseEntity("注册成功，即将返回登陆页面");
    }

    @GetMapping("getLoginUser")
    public ResponseEntity getLoginUser(){
        return new ResponseEntity(userService.getLoginUser());
    }

    @RequestMapping("isExit")
    public ResponseEntity isExit(String key,String value) {
        userService.isExit(key, value);
        return new ResponseEntity("彩蛋，如果你看到了这条消息，请务必联系我");
    }
}
