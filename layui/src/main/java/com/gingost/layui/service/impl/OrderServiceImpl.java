package com.gingost.layui.service.impl;

import com.gingost.layui.common.ResponseEntity;
import com.gingost.layui.domain.Item;
import com.gingost.layui.domain.dto.QueryCriteria.OrderInfoQueryCriteria;
import com.gingost.layui.domain.dto.QueryCriteria.OrderQueryCriteria;
import com.gingost.layui.domain.dto.resp.OrderRespDto;
import com.gingost.layui.domain.vo.LayuiTableVo;
import com.gingost.layui.domain.vo.OrderInfoVo;
import com.gingost.layui.domain.website.Order;
import com.gingost.layui.domain.website.OrderInfo;
import com.gingost.layui.mapper.OrderMapper;
import com.gingost.layui.repository.ItemJpa;
import com.gingost.layui.repository.OrderInfoJpa;
import com.gingost.layui.repository.OrderJpa;
import com.gingost.layui.service.OrderService;
import com.gingost.layui.untils.QueryHelp;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:lezzy
 * @Date:2020/4/18 13:01
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderJpa orderJpa;
    private OrderMapper orderMapper;
    private ItemJpa itemJpa;
    private OrderInfoJpa orderInfoJpa;

    @Override
    //@Cacheable(value = "order_unfinish")
    public LayuiTableVo<Order> findAllByUnfinish(int page, int size) {
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        OrderQueryCriteria req = new OrderQueryCriteria();
        req.setTypes(0);
        Page<Order> all = orderJpa.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, req, criteriaBuilder), pageable);
        List<Order> orderList = all.getContent();
        List<OrderRespDto> orderRespDtos = orderMapper.toDto(orderList);
        int count = (int) all.getTotalElements();
        return new LayuiTableVo(count, orderRespDtos);
    }

    @Override
    public LayuiTableVo findOrderInfo(OrderInfoQueryCriteria req) {
        Pageable pageable = PageRequest.of(req.getPage() - 1, req.getSize());
        Page<OrderInfo> all = orderInfoJpa.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, req, criteriaBuilder), pageable);
        int count = (int) all.getTotalElements();
        List<OrderInfo> content = all.getContent();
        List<OrderInfoVo> orderInfoVoList = new ArrayList<>();
        for (OrderInfo orderInfo : content) {
            String itemName = itemJpa.findNameById(orderInfo.getItemId());

            OrderInfoVo orderInfoVo = new OrderInfoVo(orderInfo.getId(), itemName, orderInfo.getNum());
            orderInfoVoList.add(orderInfoVo);
        }
        return new LayuiTableVo(count, orderInfoVoList);
    }

    @Override
    @Transactional
//    @Caching(evict = {
//            @CacheEvict(value = "order_unfinish", allEntries = true),
//            @CacheEvict(value = "order_finish", allEntries = true),
//            @CacheEvict(value = "order_error", allEntries = true),
//    })
    public ResponseEntity changeOrderType(Integer id, String msg) {
        ResponseEntity responseEntity = new ResponseEntity();
        Order order = orderJpa.findById(id).orElseGet(Order::new);
        if (msg.equals("edit")) {
            order.setTypes(1);
            orderJpa.save(order);
            List<OrderInfo> orderInfoList = orderInfoJpa.findOrderInfosByOrderId(order.getId());
            for (OrderInfo orderInfo:orderInfoList){
                Item item = itemJpa.findById(orderInfo.getItemId()).orElseGet(Item::new);
                item.setItemSales(orderInfo.getNum());
                item.setItemStock(item.getItemStock()-orderInfo.getNum());
                itemJpa.save(item);
            }
            responseEntity.setMsg("该订单发货成功!!!");
        } else if (msg.equals("close")) {
            order.setTypes(2);
            orderJpa.save(order);
            responseEntity.setMsg("该订单已被关闭!!!");
        }
        return responseEntity;
    }

    @Override
    //@Cacheable(value = "order_finish")
    public LayuiTableVo findFinish(int page, int size) {
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        OrderQueryCriteria req = new OrderQueryCriteria();
        req.setTypes(1);
        Page<Order> all = orderJpa.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, req, criteriaBuilder), pageable);
        List<Order> orderList = all.getContent();
        List<OrderRespDto> orderRespDtos = orderMapper.toDto(orderList);
        int count = (int) all.getTotalElements();
        return new LayuiTableVo(count, orderRespDtos);
    }

    @Override
    //@Cacheable(value = "order_error")
    public LayuiTableVo findError(int page, int size) {
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        OrderQueryCriteria req = new OrderQueryCriteria();
        req.setTypes(2);
        Page<Order> all = orderJpa.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, req, criteriaBuilder), pageable);
        List<Order> orderList = all.getContent();
        List<OrderRespDto> orderRespDtos = orderMapper.toDto(orderList);
        int count = (int) all.getTotalElements();
        return new LayuiTableVo(count, orderRespDtos);
    }
}
