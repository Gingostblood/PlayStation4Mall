package com.gingost.layui.domain.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author:lezzy
 * @Date:2020/4/18 15:59
 */
@Data
public class OrderRespDto {
    private Integer id;
    private Long uuid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm")
    private Date creatTime;
    private String address;
    private String accepter;
    private Long phone;
}
