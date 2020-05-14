package com.gingost.layui.service.impl;

import com.gingost.layui.domain.website.WebsiteUser;
import com.gingost.layui.repository.WebsiteUserJpa;
import com.gingost.layui.service.WebUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author:lezzy
 * @Date:2020/4/18 11:57
 */
@Service
@AllArgsConstructor
public class WebUserServiceImpl implements WebUserService {
    private WebsiteUserJpa websiteUserJpa;
    @Override
    public WebsiteUser findById(Integer id) {
        WebsiteUser websiteUser = websiteUserJpa.findById(1).orElseGet(WebsiteUser::new);
        return websiteUser;
    }
}
