package com.gingost.layui.domain.dto.QueryCriteria;

import com.gingost.layui.annotation.Query;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author:lezzy
 * @Date:2020/4/15 23:33
 */
@Data
public class ItemQueryCriteria implements Serializable {
    @Query
    private Integer id;

    @Query(propName = "id", type = Query.Type.IN)
    private Set<Integer> ids;
}
