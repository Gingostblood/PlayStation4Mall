package com.gingost.layui.service.impl;

import com.gingost.layui.domain.Menu;
import com.gingost.layui.repository.MenuJpa;
import com.gingost.layui.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:lezzy
 * @Date:2020/4/12 15:53
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {
    private MenuJpa menuJpa;

    //生成树结构
    @Override
    @Cacheable("MenuTree")
    public List<Map> findMenuName(Integer pid) {
        List<Menu> menuList = menuJpa.findMenuByPid(pid);
        List<Map> mapList = new ArrayList<>();
        for (Menu menu : menuList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", menu.getId());
            map.put("title", menu.getName());
            if (menu.getPid() == 0) {
                map.put("children", findMenuName(menu.getId()));
            }
            mapList.add(map);
        }
        return mapList;
    }
}
