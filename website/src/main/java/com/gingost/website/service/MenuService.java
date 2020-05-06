package com.gingost.website.service;

import com.gingost.website.domain.Menu;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/417:57
 **/
public interface MenuService {
    List<Menu> findMenuByPid(Integer pid);
}
