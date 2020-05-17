package com.gingost.website.dao;


import com.gingost.website.domain.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/1621:22
 **/
@Mapper
public interface OrderInfoDao {

    @Insert("insert into order_info values(null,#{itemId},#{num},#{price},#{orderId})")
    void saveOrderInfo(OrderInfo orderInfo);
}
