package com.gingost.website.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/1223:50
 **/
@Data
public class Orders {
    private Integer id;
    private Date creatTime;
    private Integer statu;
    private Integer types;
    private String uuid;
    private Integer userId;
    private Integer address;
    private String accepter;
    private Long phone;
}
