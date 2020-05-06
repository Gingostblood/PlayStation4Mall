package com.gingost.website.service.Impl;

import com.gingost.website.common.ShiroUtil;
import com.gingost.website.dao.UserDao;
import com.gingost.website.domain.TestUser;
import com.gingost.website.domain.WebUser;
import com.gingost.website.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/515:33
 **/
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public void saveUser(WebUser webUser) {
        String salt = UUID.randomUUID().toString();
        SimpleHash simpleHash = new SimpleHash("MD5", webUser.getPassword(), salt, 1);
        String pwd = simpleHash.toHex();
        webUser.setPassword(pwd);
        webUser.setSalt(salt);
        userDao.saveUser(webUser);
    }

    @Override
    public WebUser getLoginUser() {
        WebUser user = ShiroUtil.getLoginUser();
        return user;
    }

    @Override
    public void isExit(String key, String value) {
        int row=userDao.isExit(key, value);
        if(row!=0){
            if(key.equals("username")){
                throw new RuntimeException("用户名已存在");
            }
            if(key.equals("phone")){
                throw new RuntimeException("电话号码已存在");
            }
            if(key.equals("email")){
                throw new RuntimeException("邮箱已存在");
            }
        }
    }

    @Override
    public WebUser findUserById(Integer id) {
        return userDao.findUserById(id);
    }
}
