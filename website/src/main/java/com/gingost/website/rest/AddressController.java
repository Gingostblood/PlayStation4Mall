package com.gingost.website.rest;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.service.AddressService;
import lombok.AllArgsConstructor;
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

    @PostMapping("saveAddress")
    public void saveAddress(Integer id1,Integer id2,Integer id3,String street,String postcode){
        addressService.saveAddress(id1,id2,id3,street,postcode);
    }

    @GetMapping("findAddressById")
    public ResponseEntity findAddressById(Integer id){
        return new ResponseEntity(addressService.findAddressById(id));
    }

    @PutMapping("changeAddress")
    public ResponseEntity changeAddress(Integer id,String street,String postcode){
        addressService.changeAddress(id,street,postcode);
        return new ResponseEntity("更新成功");
    }

}
