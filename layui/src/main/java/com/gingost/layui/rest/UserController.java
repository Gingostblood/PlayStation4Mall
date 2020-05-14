package com.gingost.layui.rest;

import com.gingost.layui.annotation.Log;
import com.gingost.layui.common.ResponseEntity;
import com.gingost.layui.domain.User;
import com.gingost.layui.domain.dto.req.UserReqDto;
import com.gingost.layui.domain.dto.resp.UserRespDto;
import com.gingost.layui.domain.vo.LayuiTableVo;
import com.gingost.layui.domain.vo.LayuiTransferVo;
import com.gingost.layui.service.UserService;
import com.gingost.layui.untils.SecurityUtils;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/4/2 0:58
 */
@RestController
@RequestMapping("/user/")

public class UserController {
    @Autowired
    private UserService userService;

    @Log("查询后台管理员数据")
    @Secured("ROLE_ADMIN")
    @GetMapping("findAll")
    public LayuiTableVo<UserRespDto> findAll(int page,int size) {
        return userService.findAll(page,size);
    }

    @Log("查询管理员数据")
    @Secured("ROLE_ADMIN")
    @GetMapping("findUserByid")
    public ResponseEntity findUserByid(Integer id){
        return new ResponseEntity(userService.findUserByid(id));
    }


    @GetMapping("getLoginUser")
    public String getLoginUser(){
        return SecurityUtils.getLoginUser().getUsername();
    }

    @Log("修改管理员数据")
    @Secured("ROLE_ADMIN")
    @PutMapping("changeStatu")
    public ResponseEntity changeStatu(Integer id){
        userService.changeStatu(id);
        return new ResponseEntity("操作成功");
    }

    @Log("删除管理员数据")
    @Secured("ROLE_ADMIN")
    @DeleteMapping("deleteUserById")
    public ResponseEntity deleteUserById(Integer id){
        userService.deleteUserById(id);
        return new ResponseEntity("删除成功");
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("getTransferRole")
    public ResponseEntity getTransferRole(Integer id){
        return new ResponseEntity(userService.getTransferRole(id));
    }

    @Log("修改管理员数据")
    @Secured("ROLE_ADMIN")
    @PostMapping("changeUser")
    public String changeUser(UserReqDto req){
        return userService.changeUser(req);
    }

    @Log("新增管理员数据")
    @Secured("ROLE_ADMIN")
    @PostMapping("saveUser")
    public String saveUser(UserReqDto req){
        return userService.saveUser(req);
    }
}
