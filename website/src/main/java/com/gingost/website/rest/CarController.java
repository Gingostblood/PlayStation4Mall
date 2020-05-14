package com.gingost.website.rest;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.domain.Car;
import com.gingost.website.domain.dto.CarDto;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/60:21
 **/

@RestController
@RequestMapping("/car/")
@AllArgsConstructor
public class CarController {
    private CarService carService;

    @PostMapping("saveCar")
    public ResponseEntity saveCar(Car car){
        carService.saveCar(car);
        return  new ResponseEntity("添加购物车成功");
    }

    @GetMapping("getCarTableVo")
    public LayuiTableVo<CarDto> getCarTableVo(){
        return carService.getCarTableVo(1);
    }
}
