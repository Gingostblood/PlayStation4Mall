package com.gingost.website.service;

import com.gingost.website.domain.Car;
import com.gingost.website.domain.dto.CarDto;
import com.gingost.website.domain.vo.LayuiTableVo;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/60:14
 **/
public interface CarService {

    void saveCar(Car car);

    LayuiTableVo<CarDto> getCarTableVo(Integer userId);
}
