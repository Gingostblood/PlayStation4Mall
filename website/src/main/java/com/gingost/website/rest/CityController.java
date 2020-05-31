package com.gingost.website.rest;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/3117:14
 **/
@RestController
@RequestMapping("/city/")
@AllArgsConstructor
public class CityController {
    private CityService cityService;

    @GetMapping("findCitysByPid")
    public ResponseEntity findCitysByPid(Integer pid){
        return new ResponseEntity(cityService.findCitysByPid(pid));
    }

}
