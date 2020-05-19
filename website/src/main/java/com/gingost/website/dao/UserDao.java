package com.gingost.website.dao;

import com.gingost.website.domain.OrderInfo;
import com.gingost.website.domain.Orders;
import com.gingost.website.domain.WebUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/515:26
 **/

@Mapper
public interface UserDao {

    @Select("select * from web_user where username=#{username}")
    WebUser findUserByName(String username);

    @Insert("insert into web_user values(null,#{username},#{password},#{salt},#{phone},#{email},#{money},#{statu},now(),now())")
    void saveUser(WebUser webUser);

    @Select("select count(*) from web_user where ${key}=#{value}")
    int isExit(@Param("key")String key, @Param("value")String value);

    @Select("select * from web_user where id=#{id}")
    WebUser findUserById(Integer id);

    @Select("select * from orders where user_id =#{userid} order by id desc")
    List<Orders> finOrderByUserId(Integer userid);

    @Select("select * from order_info where order_id=#{orderId}")
    List<OrderInfo> findOrderInfoByOrderId(Integer orderId);

    @Select("select count(*) from web_user where ${key}=#{value} and id !=#{id}")
    int isHave(@Param("key")String key, @Param("value")String value,@Param("id")Integer id);

    @Update("update web_user set email=#{email},phone=#{phone},update_time=now() where id=#{id}")
    int updateUser(WebUser userById);

    @Update("update web_user set password=#{password},salt=#{salt},update_time=now() where id=#{id} ")
    int changeUserPwd(WebUser user);
}
