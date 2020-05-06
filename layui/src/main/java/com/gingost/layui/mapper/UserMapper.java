package com.gingost.layui.mapper;

import com.gingost.layui.domain.User;
import com.gingost.layui.domain.dto.resp.UserRespDto;
import com.gingost.layui.untils.BaseDE;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author:lezzy
 * @Date:2020/4/21 17:01
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseDE<UserRespDto,User> {
}
