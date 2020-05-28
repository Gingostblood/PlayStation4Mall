package com.gingost.website.rest;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.dao.AddressDao;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/290:51
 **/

@RestController
@RequestMapping("/address/")
@AllArgsConstructor
public class AddressController {
    private AddressService addressService;

    @GetMapping("findAllAddress")
    public LayuiTableVo<Map> findAllAddress(){
        return addressService.findAllAddress();
    }

    @PutMapping("deleteAddressById")
    public ResponseEntity deleteAddressById(Integer id){
        addressService.deleteAddressById(id);
        return new ResponseEntity("删除成功");
    }
}
