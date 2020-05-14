package com.gingost.layui.mapper;

import com.gingost.layui.domain.dto.resp.OrderRespDto;
import com.gingost.layui.domain.website.Order;
import com.gingost.layui.untils.BaseDE;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @Author:lezzy
 * @Date:2020/4/18 16:07
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends BaseDE<OrderRespDto, Order> {
}
