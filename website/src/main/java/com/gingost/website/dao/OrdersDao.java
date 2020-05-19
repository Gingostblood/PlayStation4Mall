package com.gingost.website.dao;

import com.gingost.website.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/1621:35
 **/

@Mapper
public interface OrdersDao {
    void saveOrder(Orders orders);

    @Update("update orders set types =#{types} where id=#{id}")
    void changeOrderTypeById(@Param("types") Integer types, @Param("id") Integer id );
}
