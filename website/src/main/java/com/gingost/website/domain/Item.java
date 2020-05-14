package com.gingost.website.domain;

import com.gingost.website.domain.common.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/316:46
 **/
@Data
public class Item extends BaseEntity implements Serializable {
    private Integer cId;
    private String itemInfo;
    private String itemLogo;
    private String itemName;
    private Double itemPrice;
    private Integer itemSales;
    private Integer itemStock;
    private Integer statu;
}
