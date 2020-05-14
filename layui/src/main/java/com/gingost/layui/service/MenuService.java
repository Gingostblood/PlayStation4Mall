package com.gingost.layui.service;

import java.util.List;
import java.util.Map;

/**
 * @Author:lezzy
 * @Date:2020/4/12 15:52ap
 */
public interface MenuService {
    List<Map> findMenuName(Integer pid);
}
