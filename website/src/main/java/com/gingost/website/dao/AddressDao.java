package com.gingost.website.dao;

import com.gingost.website.domain.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/172:34
 **/
@Mapper
public interface AddressDao {

    @Select("select * from address where user_id=#{id} and is_delete=1")
    List<Address> getUserAddressByUserId(Integer id);

    @Select("select * from address where id=#{id}")
    Address getUserAddressById(Integer id);

    @Update("UPDATE address set is_delete=0 WHERE id=#{id}")
    void deleteAddressById(Integer id);

    @Insert("insert into address values(null,#{city},#{county},#{postcode},#{province},#{street},#{userId},#{isDelete})")
    void saveAddress(Address address);

    @Update("UPDATE address set street=#{street},postcode=#{postcode} WHERE id=#{id}")
    void changeAddress(@Param("street") String street, @Param("postcode") String postcode,@Param("id") Integer id);
}