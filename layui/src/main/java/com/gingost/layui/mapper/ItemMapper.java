package com.gingost.layui.mapper;

import com.gingost.layui.domain.Item;
import com.gingost.layui.domain.dto.resp.ItemRespDto;
import com.gingost.layui.untils.BaseDE;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author:lezzy
 * @Date:2020/4/2 10:30
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper extends BaseDE<ItemRespDto, Item> {
}
