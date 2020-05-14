package com.gingost.layui.domain.dto.QueryCriteria;

import com.gingost.layui.annotation.Query;
import lombok.Data;

/**
 * @Author:lezzy
 * @Date:2020/4/18 20:00
 */
@Data
public class OrderInfoQueryCriteria {

    @Query(type = Query.Type.EQUAL)
    private Integer orderId;

    private Integer page;

    private Integer size;
}
