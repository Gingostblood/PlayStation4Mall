package com.gingost.website.domain;

import lombok.Data;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/3116:46
 **/

@Data
public class City {
    private Integer id;
    private Integer pid;
    private String cityname;
    private Integer type;
}
