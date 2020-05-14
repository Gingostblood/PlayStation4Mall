package com.gingost.website.service.Impl;

import com.gingost.website.dao.CarDao;
import com.gingost.website.dao.ItemDao;
import com.gingost.website.domain.Car;
import com.gingost.website.domain.dto.CarDto;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        int count =carList.size();
        List<CarDto> carDtoList=new ArrayList<>();
        for (Car car:carList){
            CarDto carDto=new CarDto(car,itemDao.getItemByid(car.getItemId()));
            carDtoList.add(carDto);
        }
        return new LayuiTableVo(count,carDtoList);
    }
}
