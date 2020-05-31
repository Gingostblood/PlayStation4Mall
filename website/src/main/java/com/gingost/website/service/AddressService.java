package com.gingost.website.service;

import com.gingost.website.domain.Address;
import com.gingost.website.domain.vo.LayuiTableVo;

import java.util.List;
import java.util.Map;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/290:54
 **/
public interface AddressService {
    LayuiTableVo<Map> findAllAddress();

    void deleteAddressById(Integer id);

    void saveAddress(Integer id1,Integer id2,Integer id3,String street,String postcode);

    Address findAddressById(Integer id);

    void changeAddress(Integer id, String street, String postcode);
}
