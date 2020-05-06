package com.gingost.website.domain;

import com.gingost.website.domain.common.BaseEntity;
import lombok.Data;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/60:12
 **/

@Data
public class Car {
    private Integer id;
    private Integer userId;
    private Integer itemId;
    private Double itemPrice;
    private Integer itemNum;
    private Double itemTotal;
}
