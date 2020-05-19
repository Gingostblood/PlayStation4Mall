package com.gingost.layui.domain.website;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author:lezzy
 * @Date:2020/4/17 17:10
 */

@Table(name = "order_info")
@Entity
@Getter
@Setter
@ToString
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "num")
    private Integer num;

    @Column(name = "price")
    private Double price;

    @Column(name = "order_id")
    private Integer orderId;

    /*@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Order order;*/

}
