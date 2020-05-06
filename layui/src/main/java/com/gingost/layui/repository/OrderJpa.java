package com.gingost.layui.repository;

import com.gingost.layui.domain.website.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author:lezzy
 * @Date:2020/4/18 12:56
 */
public interface OrderJpa extends JpaRepository<Order,Integer>, JpaSpecificationExecutor<Order> {
}
