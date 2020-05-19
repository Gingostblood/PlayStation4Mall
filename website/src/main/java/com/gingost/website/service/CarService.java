package com.gingost.website.service;

import com.gingost.website.domain.Address;
import com.gingost.website.domain.Car;
import com.gingost.website.domain.WebUser;
import com.gingost.website.domain.dto.CarDto;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.domain.vo.OrderVo;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/60:14
 **/
public interface CarService {

    void saveCar(Car car);

    LayuiTableVo<CarDto> getCarTableVo(Integer userId);

    ModelAndView changeCarToOrder(List<Integer> ids, String type);

    LayuiTableVo<OrderVo> findCarsByCarId(List<String> ids);

    double getPriceByCarId(List<String> ids);

    List<Address> getUserAddressByUserId(Integer id);

    WebUser getLoginUser();

    void payFortheCar(List<String> ids, Integer addressId,String accepter,String phone);
}
