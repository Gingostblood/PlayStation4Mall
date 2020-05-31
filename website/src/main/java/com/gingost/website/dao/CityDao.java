package com.gingost.website.dao;

import com.gingost.website.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/3116:48
 **/

@Mapper
public interface CityDao {

    @Select("select cityname from  city where id=#{id}")
    String findCityById(Integer id);

    @Select("select * from city where pid=#{pid}")
    List<City> findCityByPid(Integer pid);
}
