package com.gingost.website.service;

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
}
