package com.gingost.layui.repository;

import com.gingost.layui.domain.website.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author:lezzy
 * @Date:2020/4/18 19:51
 */
public interface OrderInfoJpa extends JpaRepository<OrderInfo,Integer>, JpaSpecificationExecutor<OrderInfo> {
}
