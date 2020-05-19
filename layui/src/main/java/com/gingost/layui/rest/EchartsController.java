package com.gingost.layui.rest;

import com.gingost.layui.annotation.Log;
import com.gingost.layui.common.ResponseEntity;
import com.gingost.layui.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:lezzy
 * @Date:2020/4/16 21:14
 */
@RestController
@RequestMapping("/echarts/")
public class EchartsController {
    @Autowired
    private EchartsService echartsService;

    @Log("查看柱状图")
    @GetMapping("findItemBar")
    public ResponseEntity findItemBar(Integer pid) {
        return new ResponseEntity(echartsService.toBar(pid));
    }

    @Log("查看饼状图")
    @GetMapping("findItemPie")
    public ResponseEntity findItemPie() {
        return new ResponseEntity(echartsService.toPie());
    }
}
