package com.gingost.website.dao;

import com.gingost.website.domain.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/316:51
 **/
@Mapper
public interface ItemDao {

//    @Select("select * from item")
    List<Item> findAllItem(@Param("page") int page);

    @Select("select count(*) from item")
    int getItemCount();

    @Select("select count(*) from item where c_id=#{pid}")
    int getItemCountByPid(int pid);

    @Select("select * from item where c_id=#{pid} and item_stock>0 and statu=1 limit #{page},24")
    List<Item> findAllItemByPid(@Param("pid") int pid, @Param("page") int page);

    @Select("select * from item where id=#{id}")
    Item getItemByid(Integer id);


    @Select("SELECT COUNT(*) FROM item WHERE item_name like #{s}")
    int getFuzzyQueryItemCount(String s);

    @Select("SELECT * FROM item WHERE item_name like #{s} limit #{page},24")
    List<Item> getFuzzyQueryItem(@Param("s") String s,@Param("page") int page);
}
