package com.gingost.layui.repository;

import com.gingost.layui.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @author:lezzy
 * @Date:2020/4/14 16:01
 */
public interface RoleJpa extends JpaRepository<Role,Integer>, JpaSpecificationExecutor<Role> {

    @Modifying
    @Query(value="delete from user_role where user_id = ?1",nativeQuery=true)
    void delUserRole(Integer userid);

    @Modifying
    @Query(value="insert into user_role values (?1,?2)",nativeQuery=true)
    void saveUserRole(Integer userid,Integer role_id);

    List<Role> findAllByIdIsNot(Integer id);
}
