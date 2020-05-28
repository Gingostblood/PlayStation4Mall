package com.gingost.website.domain;

import lombok.Data;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/172:33
 **/

@Data
public class Address {

    private Integer id;
    private String city;
    private String county;
    private String postcode;
    private String province;
    private String street;
    private Integer userId;
    private Integer isDelete;
}
