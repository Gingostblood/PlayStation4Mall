package com.gingost.website.dao;

import com.gingost.website.domain.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/172:34
 **/
@Mapper
public interface AddressDao {

    @Select("select * from address where user_id=#{id}")
    List<Address> getUserAddressByUserId(Integer id);

    @Select("select * from address where id=#{id}")
    Address getUserAddressById(Integer id);
}
