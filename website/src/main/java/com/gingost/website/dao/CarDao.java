package com.gingost.website.dao;

import com.gingost.website.domain.Car;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/60:14
 **/

@Mapper
public interface CarDao {

    @Insert("insert into car values (null,#{userId},#{itemId},#{itemPrice},#{itemNum},#{itemTotal})")
    void saveCar(Car car);

    @Select("select * from car where user_id=#{userId}")
    List<Car> findCarsByUserId(Integer userId);

    @Select("select id from car where user_id=#{userId}")
    List<Integer> findCarIdsByUserId(Integer id);

    List<Car> findCarsByCarId(List<Integer> ids);

    void deleteCarsById(List<Integer> ids);
}
