package com.gingost.layui.rest;

import com.gingost.layui.annotation.Log;
import com.gingost.layui.common.ResponseEntity;
import com.gingost.layui.domain.dto.QueryCriteria.OrderInfoQueryCriteria;
import com.gingost.layui.domain.dto.QueryCriteria.OrderQueryCriteria;
import com.gingost.layui.domain.dto.resp.ItemRespDto;
import com.gingost.layui.domain.vo.LayuiTableVo;
import com.gingost.layui.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:lezzy
 * @Date:2020/4/18 13:16
 */

@RestController
@Secured({"ROLE_ORDER","ROLE_ADMIN"})
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Log("查询待发货订单")
    @GetMapping("findUnfinish")
    public LayuiTableVo findUnfinish(int page, int size) {
        return orderService.findAllByUnfinish(page, size);
    }

    @Log("查询订单详情")
    @GetMapping("findOrderInfo")
    public LayuiTableVo findOrderInfo(OrderInfoQueryCriteria req) {
        return orderService.findOrderInfo(req);
    }

    @Log("关闭订单")
    @PutMapping("changeOrderType")
    public ResponseEntity changeOrderType(Integer id, String msg) {
        return orderService.changeOrderType(id, msg);
    }

    @Log("查询已发货订单")
    @GetMapping("findFinish")
    public LayuiTableVo findFinish(int page, int size) {
        return orderService.findFinish(page, size);
    }

    @Log("查询已关闭订单")
    @GetMapping("findError")
    public LayuiTableVo findError(int page, int size) {
        return orderService.findError(page, size);
    }
}
