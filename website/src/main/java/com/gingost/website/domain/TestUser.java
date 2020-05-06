package com.gingost.website.domain;

import lombok.Data;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/316:46
 **/

@Data
public class TestUser {
    private Integer id;
    private String username;
    private String pwd;
    private String salt;
}
