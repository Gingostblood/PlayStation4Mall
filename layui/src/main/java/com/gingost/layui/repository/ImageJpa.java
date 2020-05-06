package com.gingost.layui.repository;

import com.gingost.layui.domain.Image;
import com.gingost.layui.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author:lezzy
 * @Date:2020/4/6 17:10
 */
public interface ImageJpa extends JpaRepository<Image, Integer>,JpaSpecificationExecutor<Integer> {
}
