package com.gingost.website.service;

import com.gingost.website.domain.WebUser;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.domain.vo.MyOrderInfoVo;
import com.gingost.website.domain.vo.OrderVo;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/515:18
 **/
public interface UserService {

    void saveUser(WebUser webUser);

    WebUser getLoginUser();

    void isExit(String key, String value);

    WebUser findUserById(Integer id);

    List<MyOrderInfoVo> getOrderInfo();

    LayuiTableVo<OrderVo> getOrderInfoByOrderId(Integer orderId);

    void changeUser(WebUser user);
}
