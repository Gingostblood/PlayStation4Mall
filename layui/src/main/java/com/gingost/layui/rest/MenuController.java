package com.gingost.layui.rest;

import com.gingost.layui.common.ResponseEntity;
import com.gingost.layui.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:lezzy
 * @Date:2020/4/12 15:58
 */
@RestController
@RequestMapping("/")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("findParent")
    public ResponseEntity findParent(@RequestParam(defaultValue = "0", name = "id") Integer pid) {
        return new ResponseEntity(menuService.findMenuName(pid));
    }
}
