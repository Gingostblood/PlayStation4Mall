package com.gingost.layui.service;

import com.gingost.layui.domain.website.WebsiteUser;

/**
 * @Author:lezzy
 * @Date:2020/4/18 11:56
 */
public interface WebUserService {
    WebsiteUser findById(Integer id);
}
