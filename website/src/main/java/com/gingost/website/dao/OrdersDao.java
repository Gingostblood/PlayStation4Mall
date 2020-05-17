package com.gingost.website.dao;

import com.gingost.website.domain.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/1621:35
 **/

@Mapper
public interface OrdersDao {
    void saveOrder(Orders orders);
}
