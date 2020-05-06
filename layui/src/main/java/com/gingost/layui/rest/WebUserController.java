package com.gingost.layui.rest;

import com.gingost.layui.common.ResponseEntity;
import com.gingost.layui.service.WebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:lezzy
 * @Date:2020/4/18 11:58
 */

@RestController
@RequestMapping("/webuser/")
public class WebUserController {
    @Autowired
    private WebUserService webUserService;

    @GetMapping("findUser")
    public ResponseEntity findUser() {
        return new ResponseEntity(webUserService.findById(1));
    }

}
