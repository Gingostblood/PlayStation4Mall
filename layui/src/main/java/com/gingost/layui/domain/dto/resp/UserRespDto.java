package com.gingost.layui.domain.dto.resp;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/4/21 16:42
 */

@Data
public class UserRespDto {
    private Integer id;
    private String username;
    private int statu;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Long phone;
    private String email;
    private List<String> nickName;
}
