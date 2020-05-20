package com.gingost.website.dao;

import com.gingost.website.domain.Evaluate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/200:36
 **/

@Mapper
public interface EvaluateDao {

    @Select("select * from evaluate where item_id=#{itemId}")
    List<Evaluate> findAllEvaluateByItemId(Integer itemId);

    @Insert("insert into evaluate values(null,#{username},#{info},#{itemId},#{star},now())")
    void saveEvaluate(Evaluate evaluate);

    @Select("select count(*) from evaluate where item_id=#{itemId} and username=#{username}")
    int isAlreadyEvaluate(Integer itemId,String username);
}
