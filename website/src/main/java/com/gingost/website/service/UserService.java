package com.gingost.website.service;

import com.gingost.website.domain.WebUser;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/515:18
 **/
public interface UserService {

    void saveUser(WebUser webUser);

    WebUser getLoginUser();

    void isExit(String key, String value);

    WebUser findUserById(Integer id);
}
