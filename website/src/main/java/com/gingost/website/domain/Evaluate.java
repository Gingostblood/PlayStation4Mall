package com.gingost.website.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/200:35
 **/
@Data
public class Evaluate {
    private Integer id;
    private String username;
    private String info;
    private Integer itemId;
    private Integer star;
    @JsonFormat(pattern = "yyyy-HH-dd")
    private Timestamp createTime;
}
