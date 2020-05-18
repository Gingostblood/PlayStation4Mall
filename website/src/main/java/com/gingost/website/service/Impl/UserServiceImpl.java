package com.gingost.website.service.Impl;

import com.gingost.website.common.ShiroUtil;
import com.gingost.website.dao.ItemDao;
import com.gingost.website.dao.UserDao;
import com.gingost.website.domain.OrderInfo;
import com.gingost.website.domain.Orders;
import com.gingost.website.domain.TestUser;
import com.gingost.website.domain.WebUser;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.domain.vo.MyOrderInfoVo;
import com.gingost.website.domain.vo.OrderVo;
import com.gingost.website.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/515:33
 **/
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private ItemDao itemDao;

    @Override
    @Transactional
    public void saveUser(WebUser webUser) {
        String salt = UUID.randomUUID().toString();
        SimpleHash simpleHash = new SimpleHash("MD5", webUser.getPassword(), salt, 1);
        String pwd = simpleHash.toHex();
        webUser.setPassword(pwd);
        webUser.setSalt(salt);
        userDao.saveUser(webUser);
    }

    @Override
    public WebUser getLoginUser() {
        WebUser user = ShiroUtil.getLoginUser();
       if(Objects.nonNull(user)) {
           return user;
       }else {
           WebUser visitor=new WebUser();
           visitor.setUsername("游客");
           return visitor;
       }
    }

    @Override
    public void isExit(String key, String value) {
        int row=userDao.isExit(key, value);
        if(row!=0){
            if(key.equals("username")){
                throw new RuntimeException("用户名已存在");
            }
            if(key.equals("phone")){
                throw new RuntimeException("电话号码已存在");
            }
            if(key.equals("email")){
                throw new RuntimeException("邮箱已存在");
            }
        }
    }

    @Override
    public WebUser findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<MyOrderInfoVo> getOrderInfo() {
        List<MyOrderInfoVo> list=new ArrayList<>();
        Integer userid=ShiroUtil.getLoginUser().getId();
        List<Orders> ordersList=userDao.finOrderByUserId(userid);
        for (Orders orders:ordersList){
            MyOrderInfoVo myOrderInfoVo=new MyOrderInfoVo();
            myOrderInfoVo.setId(orders.getId());
            myOrderInfoVo.setUuid(orders.getUuid());
            if (orders.getTypes()==0){
                myOrderInfoVo.setBtnName("取消订单");
            }
            if(orders.getTypes()==1){
                myOrderInfoVo.setBtnName("确认收货");
            }
            list.add(myOrderInfoVo);
        }
        return list;
    }

    @Override
    public LayuiTableVo<OrderVo> getOrderInfoByOrderId(Integer orderId) {
        List<OrderInfo> orderInfoList = userDao.findOrderInfoByOrderId(orderId);

        List<OrderVo> list=new ArrayList<>();
        for (OrderInfo orderInfo:orderInfoList){
            OrderVo orderVo=new OrderVo();
            orderVo.setName(itemDao.getItemByid(orderInfo.getItemId()).getItemName());
            orderVo.setNum(orderInfo.getNum());
            list.add(orderVo);
        }
        return new LayuiTableVo<>(list.size(),list);
    }
}
