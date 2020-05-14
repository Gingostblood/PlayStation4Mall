package com.gingost.layui.domain.dto.QueryCriteria;

import com.gingost.layui.annotation.Query;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author:lezzy
 * @Date:2020/4/18 13:02
 */
@Data
public class OrderQueryCriteria implements Serializable {
    @Query(type = Query.Type.EQUAL)
     private Integer types;
}
