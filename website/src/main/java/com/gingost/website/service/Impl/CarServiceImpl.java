package com.gingost.website.service.Impl;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.common.ShiroUtil;
import com.gingost.website.dao.*;
import com.gingost.website.domain.*;
import com.gingost.website.domain.dto.CarDto;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.domain.vo.OrderVo;
import com.gingost.website.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/60:17
 **/
@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private CarDao carDao;
    private ItemDao itemDao;
    private AddressDao addressDao;
    private UserDao userDao;
    private OrdersDao ordersDao;
    private OrderInfoDao orderInfoDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCar(Car car) {
        if (Objects.nonNull(car)) {
            car.setItemTotal(car.getItemPrice() * car.getItemNum());
            carDao.saveCar(car);
        } else
            throw new RuntimeException("该商品已被下架");

    }

    @Override
    public LayuiTableVo<CarDto> getCarTableVo(Integer userId) {
        List<Car> carList = carDao.findCarsByUserId(userId);
        int count = carList.size();
        List<CarDto> carDtoList = new ArrayList<>();
        for (Car car : carList) {
            CarDto carDto = new CarDto(car, itemDao.getItemByid(car.getItemId()));
            carDtoList.add(carDto);
        }
        return new LayuiTableVo(count, carDtoList);
    }

    @Override
    public ModelAndView changeCarToOrder(List<Integer> ids, String type) {
        ModelAndView modelAndView = new ModelAndView();
        if (type.equals("yes")) {
            List<Integer> carIds = carDao.findCarIdsByUserId(ShiroUtil.getLoginUser().getId());
            modelAndView.addObject("ids", carIds);
        } else {
            modelAndView.addObject("ids", ids);
        }
        modelAndView.setViewName("sys/order");
        return modelAndView;
    }

    @Override
    public LayuiTableVo<OrderVo> findCarsByCarId(List<String> ids) {
        List<Car> carList = carDao.findCarsByCarId(doHnadlerListFormat(ids));
        List<OrderVo> list = new ArrayList<>();
        for (Car car : carList) {
            OrderVo orderVo = new OrderVo();
            orderVo.setName(itemDao.getItemByid(car.getItemId()).getItemName());
            orderVo.setNum(car.getItemNum());
            list.add(orderVo);
        }
        int count = carList.size();
        return new LayuiTableVo<>(count, list);
    }

    @Override
    public double getPriceByCarId(List<String> ids) {
        List<Car> carList = carDao.findCarsByCarId(doHnadlerListFormat(ids));
        double price = 0;
        for (Car car : carList) {
            price += car.getItemTotal();
        }
        return price;
    }

    @Override
    public List<Address> getUserAddressByUserId(Integer id) {
        return addressDao.getUserAddressByUserId(id);
    }

    @Override
    public WebUser getLoginUser() {
        WebUser user = ShiroUtil.getLoginUser();
        if (Objects.nonNull(user)) {
            return user;
        } else {
            WebUser visitor = new WebUser();
            visitor.setUsername("游客");
            return visitor;
        }
    }

    @Override
    public void payFortheCar(List<String> ids, Address address,String accepter,String phone) {
        List<Car> carList = carDao.findCarsByCarId(doHnadlerListFormat(ids));
        WebUser user = userDao.findUserById(ShiroUtil.getLoginUser().getId());
        double price = 0;
        for (Car car : carList) {
            price += car.getItemTotal();
        }
        //生成订单
        Orders orders=new Orders();
        orders.setAccepter(accepter);
        orders.setAddress(address.getProvince()+"省"+address.getCounty()+"市"+address.getCity()+"县"+address.getStreet()+"街道"+address.getPostcode());
        orders.setPhone(Long.parseLong(phone));
        orders.setUuid(String.valueOf(System.currentTimeMillis()));
        orders.setUserId(user.getId());

        ordersDao.saveOrder(orders);
        //生成订单详情
        for (Car car:carList){
            OrderInfo orderInfo=new OrderInfo();
            orderInfo.setItemId(car.getItemId());
            orderInfo.setNum(car.getItemNum());
            orderInfo.setPrice(price);
            orderInfo.setOrderId(orders.getId());
            orderInfoDao.saveOrderInfo(orderInfo);
        }

        carDao.deleteCarsById(doHnadlerListFormat(ids));
    }

    public List<Integer> doHnadlerListFormat(List<String> ids) {
        List<Integer> integerList = new ArrayList<>();
        for (String str : ids) {
            integerList.add(Integer.parseInt(str.replaceAll("\\[", "").replaceAll("\\]", "")));
        }
        return integerList;
    }
}
