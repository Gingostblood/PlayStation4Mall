package com.gingost.website.service.Impl;

import com.gingost.website.dao.AddressDao;
import com.gingost.website.dao.CityDao;
import com.gingost.website.domain.Address;
import com.gingost.website.domain.vo.LayuiTableVo;
import com.gingost.website.service.AddressService;
import com.gingost.website.utils.ShiroUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/290:55
 **/

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao;
    private CityDao cityDao;

    @Override
    public LayuiTableVo<Map> findAllAddress() {
        List<Map> list = new ArrayList<>();
        List<Address> addressList = addressDao.getUserAddressByUserId(ShiroUtil.getLoginUser().getId());
        for (Address address : addressList) {
            String s = address.getProvince() + address.getCounty() + address.getCity() + address.getStreet();
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", address.getId());
            map.put("address", s);
            map.put("postcode", address.getPostcode());
            list.add(map);
        }
        return new LayuiTableVo(list.size(), list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAddressById(Integer id) {
        try {
            addressDao.deleteAddressById(id);
        } catch (Exception e) {
            throw new RuntimeException("该条记录已经不存在，请刷新页面重试");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAddress(Integer id1, Integer id2, Integer id3, String street, String postcode) {
        Address address = new Address();
        String pro = cityDao.findCityById(id1);
        String county = cityDao.findCityById(id2);
        String city = cityDao.findCityById(id3);
        address.setProvince(pro + "省");
        address.setCounty(county + "市");
        address.setCity(city);
        address.setIsDelete(1);
        address.setStreet(street);
        address.setPostcode(postcode);
        address.setUserId(ShiroUtil.getLoginUser().getId());
        try {
            addressDao.saveAddress(address);
        } catch (Exception e) {
            throw new RuntimeException("操作失败，请重试");
        }
    }

    @Override
    public Address findAddressById(Integer id) {
        return addressDao.getUserAddressById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeAddress(Integer id, String street, String postcode) {
        try {
            addressDao.changeAddress(street, postcode, id);
        } catch (Exception e) {
            throw new RuntimeException("操作失败");
        }
    }
}
