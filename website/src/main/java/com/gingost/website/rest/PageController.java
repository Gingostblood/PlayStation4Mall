package com.gingost.website.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/316:33
 **/

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class PageController {
    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("getItemInfo")
    public ModelAndView getItemInfo(String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", Integer.parseInt(id));
        modelAndView.setViewName("sys/item_info");
        return modelAndView;
    }

    @GetMapping("signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("car")
    public String car() {
        return "sys/car_test";
    }

    @GetMapping("myinfo")
    public String myinfo() {
        return "sys/myinfo";
    }

    @GetMapping("evaluate")
    public ModelAndView evaluate(Integer id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("id",id);
        modelAndView.setViewName("sys/evaluate");
        return modelAndView;
    }

//    @RequestMapping("order")
//    public String order() {
//        return "sys/order";
//    }
}
