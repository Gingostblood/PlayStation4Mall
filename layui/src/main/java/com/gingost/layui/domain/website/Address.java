package com.gingost.layui.domain.website;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author:lezzy
 * @Date:2020/4/17 16:32
 */

@Table(name = "address")
@Entity
@Getter
@Setter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "county")
    private String county;

    @Column(name = "street")
    private String street;

    @Column(name = "postcode")
    private String postcode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private WebsiteUser websiteUser;
}
