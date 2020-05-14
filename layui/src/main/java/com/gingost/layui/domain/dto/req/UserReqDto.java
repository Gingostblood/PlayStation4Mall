package com.gingost.layui.domain.dto.req;


import lombok.Data;

import java.util.Set;

@Data
public class UserReqDto {
    private Integer id;
    private String username;
    private String password;
    private Long phone;
    private String email;
    private Set<Integer> roles;
}
