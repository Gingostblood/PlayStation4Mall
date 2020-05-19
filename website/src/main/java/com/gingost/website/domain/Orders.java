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
    //0 未支付 1已支付
    private Integer statu=0;
    //0 未发货 1已发货 2异常关闭 3完成
    private Integer types=0;
    private String uuid;
    private Integer userId;
    private String address;
    private String accepter;
    private Long phone;
}
