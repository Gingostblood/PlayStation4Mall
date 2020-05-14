package com.gingost.website.service.Impl;

import com.gingost.website.dao.MenuDao;
import com.gingost.website.domain.Menu;
import com.gingost.website.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/417:57
 **/
@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {
    private MenuDao menuDao;
    @Override
    public List<Menu> findMenuByPid(Integer pid) {
        return menuDao.findMenuByPid(pid);
    }
}
