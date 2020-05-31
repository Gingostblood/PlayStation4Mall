package com.gingost.website.service;

import com.gingost.website.domain.Evaluate;
import com.gingost.website.domain.WebUser;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.domain.vo.MyOrderInfoVo;
import com.gingost.website.domain.vo.OrderVo;

import java.util.List;
import java.util.Map;

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

    List<MyOrderInfoVo> getOrderInfo(Integer page,Integer size);

    LayuiTableVo<OrderVo> getOrderInfoByOrderId(Integer orderId);

    void changeUser(WebUser user);

    Integer getUserOrdersCount();

    int changePwd(String oldpwd, String newpwd);

    int changeOrderType(String type,Integer id);

    Integer getHistoryUserOrdersCount();

    List<MyOrderInfoVo> getHistoryUserOrders(Integer page, Integer size);

    List<Map> findEvaluateList(Integer orderid);

    int goToEvaluate(Evaluate evaluate);

    void forgetPwd(Long phone, String email, String pwd);
}
