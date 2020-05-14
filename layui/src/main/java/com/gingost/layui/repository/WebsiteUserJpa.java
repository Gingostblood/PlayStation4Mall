package com.gingost.layui.repository;

import com.gingost.layui.domain.website.WebsiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author:lezzy
 * @Date:2020/4/18 11:54
 */
public interface WebsiteUserJpa extends JpaRepository<WebsiteUser,Integer>, JpaSpecificationExecutor<WebsiteUser> {
}
