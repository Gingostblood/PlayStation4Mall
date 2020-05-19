package com.gingost.layui.domain.website;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author:lezzy
 * @Date:2020/4/17 17:05
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
    private Long uuid;

    @Column(name = "creat_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss:mm")
    private Date creatTime;

    //订单状态 0：未支付 1:已支付
    @Column(name = "statu")
    private Integer statu;

    //订单类型 0:未发货 1:已发货 2:关闭
    @Column(name = "types")
    private Integer types=0;

    @Column(name = "address")
    private String address;

    @Column(name = "accepter")
    private String accepter;

    @Column(name = "phone")
    private Long phone;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private WebsiteUser websiteUser;

    /*@OneToMany(mappedBy = "order")
    @ToString.Exclude
    private Set<OrderInfo> orderInfos;*/
}
