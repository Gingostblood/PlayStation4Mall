package com.gingost.website.rest;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.domain.Car;
import com.gingost.website.domain.dto.CarDto;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.domain.vo.OrderVo;
import com.gingost.website.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/60:21
 **/

@Controller
@RequestMapping("/car/")
@AllArgsConstructor
public class CarController {
    private CarService carService;

    @PostMapping("saveCar")
    @ResponseBody
    public ResponseEntity saveCar(Car car){
        carService.saveCar(car);
        return  new ResponseEntity("添加购物车成功");
    }

    @GetMapping("getCarTableVo")
    @ResponseBody
    public LayuiTableVo<CarDto> getCarTableVo(Integer id){
        return carService.getCarTableVo(id);
    }


    @GetMapping("changeCarToOrder")
    @ResponseBody
    public ModelAndView changeCarToOrder(@RequestParam(value = "ids") List<Integer> ids,String type){
        return carService.changeCarToOrder(ids,type);
    }

    //Order
    @GetMapping("findCarsByCarId")
    @ResponseBody
    public LayuiTableVo<OrderVo> findCarsByCarId(@RequestParam(value = "ids")List<String> ids){
        return carService.findCarsByCarId(ids);
    }

    @GetMapping("getPriceByCarId")
    @ResponseBody
    public double getPriceByCarId(@RequestParam(value = "ids") List<String> ids){
        return carService.getPriceByCarId(ids);
    }

    @GetMapping("getUserAddressByUserId")
    @ResponseBody
    public ResponseEntity getUserAddressByUserId(Integer id){
        return new ResponseEntity(carService.getUserAddressByUserId(id));
    }

    @GetMapping("getLoginUser")
    @ResponseBody
    public ResponseEntity getLoginUser(){
        return new ResponseEntity(carService.getLoginUser());
    }

    @PutMapping("payFortheCar")
    @ResponseBody
    public ResponseEntity payFortheCar(@RequestParam(value = "ids") List<String> ids,Integer addressId,String accepter,String phone){
        carService.payFortheCar(ids,addressId,accepter,phone);
        return new ResponseEntity("付款成功");
    }

    @GetMapping("index")
    public String index(){
        return "redirect:/index";
    }
}
