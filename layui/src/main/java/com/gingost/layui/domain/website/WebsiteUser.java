package com.gingost.layui.domain.website;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * @author:lezzy
 * @Date:2020/4/17 16:26
 */

@Table(name = "web_user")
@Entity
@Data
public class WebsiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "email")
    private String email;

    @Column(name = "statu")
    private Boolean statu=true;

    @Column(name = "salt")
    private String salt;


    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @OneToMany(mappedBy = "websiteUser")
    private Set<Address> addresses;

    @OneToMany(mappedBy = "websiteUser")
    private Set<Order> orders;
}
