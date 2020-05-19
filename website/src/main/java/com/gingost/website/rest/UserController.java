package com.gingost.website.rest;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.domain.WebUser;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.domain.vo.OrderVo;
import com.gingost.website.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity findOneUser(WebUser webUser) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(webUser.getUsername(), webUser.getPassword());
        subject.login(token);
        return new ResponseEntity("登陆成功");
    }

    @PostMapping("saveUser")
    public ResponseEntity saveUser(WebUser webUser) {
        userService.saveUser(webUser);
        return new ResponseEntity("注册成功，即将返回登陆页面");
    }

    @GetMapping("getLoginUser")
    public ResponseEntity getLoginUser() {
        return new ResponseEntity(userService.getLoginUser());
    }

    @RequestMapping("isExit")
    public ResponseEntity isExit(String key, String value) {
        userService.isExit(key, value);
        return new ResponseEntity("彩蛋，如果你看到了这条消息，请务必联系我");
    }

    @GetMapping("getUserOrders")
    private ResponseEntity getOrderInfo(Integer page,Integer size) {
        return new ResponseEntity(userService.getOrderInfo(page,size));
    }

    @GetMapping("getOrderInfoByOrderId")
    public LayuiTableVo<OrderVo> getOrderInfoByOrderId(Integer orderId) {
        return userService.getOrderInfoByOrderId(orderId);
    }

    @PutMapping("changeUser")
    public ResponseEntity changeUser(WebUser user){
        userService.changeUser(user);
        return new ResponseEntity("更新成功");
    }

    @GetMapping("findUserByid")
    public ResponseEntity findUserByid(Integer id){
        return new ResponseEntity(userService.findUserById(id));
    }

    @GetMapping("getUserOrdersCount")
    public Integer getUserOrdersCount(){
        return userService.getUserOrdersCount();
    }

    @PutMapping("changePwd")
    public ResponseEntity changePwd(String oldpwd,String newpwd){
        return new ResponseEntity(userService.changePwd(oldpwd,newpwd),"修改密码成功，下次登录时将会生效");
    }
}
