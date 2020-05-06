package com.gingost.layui.domain.dto.QueryCriteria;

import com.gingost.layui.annotation.Query;
import lombok.Data;

/**
 * @author:lezzy
 * @Date:2020/4/21 16:52
 */
@Data
public class UserQueryCriteria {
    @Query(type = Query.Type.NOT_EQUAL)
    private Integer id;
}
