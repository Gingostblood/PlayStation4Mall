package com.gingost.layui.domain.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author:lezzy
 * @Date:2020/4/2 10:26
 */
@Data
public class ItemRespDto {
    private Integer id;
    private String itemName;
    private String itemPrice;
    private Integer itemStock;
    private Integer itemSales;
    @JsonIgnore
    private Integer cId;
    private String menuName;
    private Integer statu;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "creat_time")
    private Date creatTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "update_time")
    private Date updateTime;
}
