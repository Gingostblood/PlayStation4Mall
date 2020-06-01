package com.gingost.layui.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author:lezzy
 * @Date:2020/4/1 16:09
 */
@Controller
@RequestMapping("/")
public class PageController {

    @RequestMapping("{module}/{moduleUI}")
    public String doModuleUI(
            @PathVariable String moduleUI) {
        return "sys/" + moduleUI;
    }




    @RequestMapping("index")
    public String index() {
        return "index";
    }

    //===========================================item
    @Secured({"ROLE_ITEM", "ROLE_ADMIN"})
    @RequestMapping("goods/goods_list")
    public String goods_list() {
        return "sys/goods_list";
    }

    @Secured({"ROLE_ITEM", "ROLE_ADMIN"})
    @RequestMapping("goods/goods_analyse")
    public String goods_analyse() {
        return "sys/goods_analyse";
    }



    //===========================================order

    @Secured({"ROLE_ORDER", "ROLE_ADMIN"})
    @RequestMapping("orders/orders_finish")
    public String order1() {
        return "sys/orders_finish";
    }

    @Secured({"ROLE_ORDER", "ROLE_ADMIN"})
    @RequestMapping("orders/orders_error")
    public String order2() {
        return "sys/orders_error";
    }

    @Secured({"ROLE_ORDER", "ROLE_ADMIN"})
    @RequestMapping("orders/orders_unfinish")
    public String order3() {
        return "sys/orders_unfinish";
    }


    /*@Secured({"ROLE_ITEM", "ROLE_ADMIN"})
    @RequestMapping("goods/goods_add")
    public String goods_add() {
        return "sys/goods_add";
    }*/

    //===========================================user

    @Secured("ROLE_ADMIN")
    @RequestMapping("users/users_list")
    public String users_list() {
        return "sys/users_list";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("log/log_list")
    public String log_list() {
        return "sys/log_list";
    }

    //===========================================

    @RequestMapping("403.html")
    public String exceptionNo1() {
        return "403";
    }


    //===========================================
    @RequestMapping("goods_add")
    public String goods_add() {
        return "sys/goods_add";
    }

    @RequestMapping("users_add")
    public String users_add() {
        return "sys/users_add";
    }

    //===========================================modelview

    @RequestMapping("orders_info")
    public ModelAndView orders_info(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("order_id", id);
        modelAndView.setViewName("sys/orders_info");
        return modelAndView;
    }

    @RequestMapping("users_info")
    public ModelAndView users_info(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user_id", id);
        modelAndView.setViewName("sys/users_info");
        return modelAndView;
    }

    @RequestMapping("goods_update")
    public ModelAndView goods_update(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        modelAndView.setViewName("sys/goods_update");
        return modelAndView;
    }

    //===========================================
    @RequestMapping("login.html")
    public String login() {
        return "login";
    }



    //
    @Secured({"ROLE_ADMIN", "ROLE_WEB"})
    @RequestMapping("image/image_rush")
    public String rush(){
        return "sys/image_rush";
    }

    @Secured({"ROLE_ADMIN", "ROLE_WEB"})
    @RequestMapping("news/news_push")
    public String push(){
        return "sys/news_push";
    }

}
