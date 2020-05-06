package com.gingost.website.rest;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/417:58
 **/

@RestController
@RequestMapping("/menu/")
@AllArgsConstructor
public class MenuController {
    private MenuService menuService;

    @GetMapping("findMenuByPid")
    public ResponseEntity findMenuByPid(Integer pid){
        return new ResponseEntity(menuService.findMenuByPid(pid));
    }
}
