package com.gingost.layui.rest;

import com.gingost.layui.domain.system.Log;
import com.gingost.layui.domain.vo.LayuiTableVo;
import com.gingost.layui.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lezzy
 * @description
 * @data 2020/6/12:32
 **/

@RestController
@RequestMapping("/log/")
@AllArgsConstructor
public class LogController {
    private LogService logService;

    @GetMapping("findAll")
    public LayuiTableVo<Log> findAll(int page,int size){
        return logService.findAll(page,size);
    }
}
