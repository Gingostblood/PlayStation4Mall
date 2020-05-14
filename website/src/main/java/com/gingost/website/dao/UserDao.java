package com.gingost.website.dao;

import com.gingost.website.domain.TestUser;
import com.gingost.website.domain.WebUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/515:26
 **/

@Mapper
public interface UserDao {

    @Select("select * from web_user where username=#{username}")
    WebUser findUserByName(String username);

    @Insert("insert into web_user values(null,#{username},#{password},#{salt},#{phone},#{email},#{money},#{statu},now(),now())")
    void saveUser(WebUser webUser);

    @Select("select count(*) from web_user where ${key}=#{value}")
    public int isExit(@Param("key")String key, @Param("value")String value);

    @Select("select * from web_user where id=#{id}")
    public WebUser findUserById(Integer id);

}
