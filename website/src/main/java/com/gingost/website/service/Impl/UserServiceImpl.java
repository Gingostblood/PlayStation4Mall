package com.gingost.website.service.Impl;

import com.gingost.website.common.ShiroUtil;
import com.gingost.website.dao.EvaluateDao;
import com.gingost.website.dao.ItemDao;
import com.gingost.website.dao.OrdersDao;
import com.gingost.website.dao.UserDao;
import com.gingost.website.domain.Evaluate;
import com.gingost.website.domain.OrderInfo;
import com.gingost.website.domain.Orders;
import com.gingost.website.domain.WebUser;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.domain.vo.MyOrderInfoVo;
import com.gingost.website.domain.vo.OrderVo;
import com.gingost.website.service.UserService;
import com.gingost.website.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private OrdersDao ordersDao;
    private EvaluateDao evaluateDao;

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
        if (Objects.nonNull(user)) {
            return userDao.findUserById(user.getId());
        } else {
            WebUser visitor = new WebUser();
            visitor.setUsername("游客");
            return visitor;
        }
    }

    @Override
    public void isExit(String key, String value) {
        int row = userDao.isExit(key, value);
        if (row != 0) {
            if (key.equals("username")) {
                throw new RuntimeException("用户名已存在");
            }
            if (key.equals("phone")) {
                throw new RuntimeException("电话号码已存在");
            }
            if (key.equals("email")) {
                throw new RuntimeException("邮箱已存在");
            }
        }
    }

    @Override
    public WebUser findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<MyOrderInfoVo> getOrderInfo(Integer page, Integer size) {
        List<MyOrderInfoVo> list = new ArrayList<>();
        Integer userid = ShiroUtil.getLoginUser().getId();
        List<Orders> ordersList = userDao.findOrderByUserId(userid);
        for (Orders orders : ordersList) {
            MyOrderInfoVo myOrderInfoVo = new MyOrderInfoVo();
            myOrderInfoVo.setId(orders.getId());
            myOrderInfoVo.setUuid(orders.getUuid());
            if (orders.getTypes() == 0) {
                myOrderInfoVo.setBtnName("取消订单");
            }
            if (orders.getTypes() == 1) {
                myOrderInfoVo.setBtnName("确认收货");
            }
            list.add(myOrderInfoVo);
        }
        return PageUtils.toPage(page - 1, size, list);
    }

    @Override
    public LayuiTableVo<OrderVo> getOrderInfoByOrderId(Integer orderId) {
        List<OrderInfo> orderInfoList = userDao.findOrderInfoByOrderId(orderId);

        List<OrderVo> list = new ArrayList<>();
        for (OrderInfo orderInfo : orderInfoList) {
            OrderVo orderVo = new OrderVo();
            orderVo.setName(itemDao.getItemByid(orderInfo.getItemId()).getItemName());
            orderVo.setNum(orderInfo.getNum());
            list.add(orderVo);
        }
        return new LayuiTableVo<>(list.size(), list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeUser(WebUser user) {
        int rowA = userDao.isHave("email", user.getEmail(), user.getId());
        if (rowA > 0) {
            throw new RuntimeException("该邮箱已被注册，请重新填写");
        }
        int rowB = userDao.isHave("phone", String.valueOf(user.getPhone()), user.getId());
        if (rowB > 0) {
            throw new RuntimeException("该电话已被注册，请重新填写");
        }
        WebUser userById = userDao.findUserById(user.getId());
        userById.setEmail(user.getEmail());
        userById.setPhone(user.getPhone());
        userById.setUpdateTime(new Date());
        int i = userDao.updateUser(userById);
        if (i != 1) {
            throw new RuntimeException("发生了不可知错误，请重试");
        }
    }

    @Override
    public Integer getUserOrdersCount() {
        return userDao.findOrderByUserId(ShiroUtil.getLoginUser().getId()).size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changePwd(String oldpwd, String newpwd) {
        WebUser user = userDao.findUserById(ShiroUtil.getLoginUser().getId());
        String hashPwd = new SimpleHash("MD5", oldpwd, user.getSalt(), 1).toHex();
        if (user.getPassword().equals(hashPwd)) {
            String salt = UUID.randomUUID().toString();
            String newPwd = new SimpleHash("MD5", newpwd, salt, 1).toHex();
            user.setSalt(salt);
            user.setPassword(newPwd);
            int i=userDao.changeUserPwd(user);
            return i;
        }else {
            throw new RuntimeException("原密码错误");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeOrderType(String type,Integer id) {
        try {
            if (type.equals("close")){
                ordersDao.changeOrderTypeById(2,id);
            }else if(type.equals("sure")){
                ordersDao.changeOrderTypeById(3,id);
            }
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
        return 0;
    }

    @Override
    public Integer getHistoryUserOrdersCount() {
        return userDao.findHistoryOrderByUserId(ShiroUtil.getLoginUser().getId()).size();
    }

    @Override
    public List<MyOrderInfoVo> getHistoryUserOrders(Integer page, Integer size) {
        List<MyOrderInfoVo> list = new ArrayList<>();
        Integer userid = ShiroUtil.getLoginUser().getId();
        List<Orders> ordersList = userDao.findHistoryOrderByUserId(userid);
        for (Orders orders : ordersList) {
            MyOrderInfoVo myOrderInfoVo = new MyOrderInfoVo();
            myOrderInfoVo.setId(orders.getId());
            myOrderInfoVo.setUuid(orders.getUuid());
            list.add(myOrderInfoVo);
        }
        return PageUtils.toPage(page - 1, size, list);
    }

    @Override
    public List<Map> findEvaluateList(Integer orderid) {
        List<OrderInfo> orderInfoList = userDao.findOrderInfoByOrderId(orderid);
        List<Map> list=new ArrayList<>();
        for (OrderInfo orderInfo:orderInfoList){
            Map<String,Object> map=new LinkedHashMap<>();
            map.put("id",orderInfo.getItemId());
            map.put("name",itemDao.getItemByid(orderInfo.getItemId()).getItemName());
            list.add(map);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int goToEvaluate(Evaluate evaluate) {
        int i=0;
        try {
            evaluate.setUsername(ShiroUtil.getLoginUser().getUsername());
            evaluateDao.saveEvaluate(evaluate);
        } catch (Exception e) {
           throw new RuntimeException("服务器开了个小差");
        }
        return 0;
    }
}
