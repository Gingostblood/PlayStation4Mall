package com.gingost.layui.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author:lezzy
 * @Date:2020/4/18 19:47
 */

@Data
@AllArgsConstructor
public class OrderInfoVo {
    private Integer id;
    private String itemName;
    private Integer num;
}
