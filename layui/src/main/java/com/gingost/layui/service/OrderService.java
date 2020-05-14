package com.gingost.layui.service;


import com.gingost.layui.common.ResponseEntity;
import com.gingost.layui.domain.dto.QueryCriteria.OrderInfoQueryCriteria;
import com.gingost.layui.domain.vo.LayuiTableVo;
import com.gingost.layui.domain.website.Order;


/**
 * @Author:lezzy
 * @Date:2020/4/18 12:59
 */
public interface OrderService {
    LayuiTableVo<Order> findAllByUnfinish(int page, int size);

    LayuiTableVo findOrderInfo(OrderInfoQueryCriteria req);

    ResponseEntity changeOrderType(Integer id, String msg);

    LayuiTableVo findFinish(int page, int size);

    LayuiTableVo findError(int page, int size);
}
