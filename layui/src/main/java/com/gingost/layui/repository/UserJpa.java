package com.gingost.layui.repository;

import com.gingost.layui.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author:lezzy
 * @Date:2020/4/2 0:55
 */
public interface UserJpa extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
    User findUserByUsername(String username);

    User findUserByPhone(Long phone);

    User findUserByEmail(String email);
}
