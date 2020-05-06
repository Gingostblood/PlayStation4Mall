package com.gingost.website.dao;

import com.gingost.website.domain.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/417:52
 **/
@Mapper
public interface MenuDao {

    @Select("select * from menu where pid=#{pid}")
    List<Menu> findMenuByPid(Integer pid);
}
