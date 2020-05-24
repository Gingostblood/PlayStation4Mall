package com.gingost.website.dao;

import com.gingost.website.domain.Images;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/2422:17
 **/

@Mapper
public interface ImageDao {

    @Select("select * from images")
    List<Images> findAll();
}
