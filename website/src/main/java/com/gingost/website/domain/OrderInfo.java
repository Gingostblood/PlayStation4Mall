package com.gingost.website.domain;

import lombok.Data;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/16 03:21
 **/

@Data
public class OrderInfo {
    private Integer id;
    private Integer itemId;
    private Integer num;
    private Double price;
    private Integer orderId;
}
