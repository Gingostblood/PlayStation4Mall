package com.gingost.website.domain;

import com.gingost.website.domain.common.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/623:10
 **/

@Data
public class WebUser extends BaseEntity {
    private String username;
    private String password;
    private Long phone;
    private String salt;
    private String email;
    private Integer statu=1;
    private BigDecimal money=BigDecimal.ZERO;

}
