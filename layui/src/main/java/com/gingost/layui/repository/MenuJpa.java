package com.gingost.layui.repository;

import com.gingost.layui.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author:lezzy
 * @Date:2020/4/12 15:48
 */
public interface MenuJpa extends JpaRepository<Menu,Integer>, JpaSpecificationExecutor<Menu> {
    List<Menu> findMenuByPid(Integer pid);

    @Query(value = "SELECT CONCAT(m1.name,'/',m2.name) FROM menu m1,menu m2 WHERE m1.id=m2.pid AND m2.id=?1",nativeQuery = true)
    String findNameById(Integer id);
}
