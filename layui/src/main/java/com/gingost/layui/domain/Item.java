package com.gingost.layui.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author:lezzy
 * @Date:2020/4/2 10:04
 */
@Entity
@Table(name = "item")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_logo")
    private String itemLogo;
    @Column(name = "item_info")
    private String itemInfo;
    @Column(name = "item_price")
    private Double itemPrice;
    @Column(name = "item_stock")
    private Integer itemStock;
    @Column(name = "item_sales")
    private Integer itemSales;
    @Column(name = "c_id")
    private Integer cId;
    @Column(name = "statu")
    private Integer statu;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "creat_time")
    private Date creatTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;
}
