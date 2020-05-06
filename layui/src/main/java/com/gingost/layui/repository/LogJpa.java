package com.gingost.layui.repository;

import com.gingost.layui.domain.system.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LogJpa extends JpaRepository<Log,Integer>, JpaSpecificationExecutor<Log> {
}
