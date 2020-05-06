package com.gingost.layui.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author:lezzy
 * @Date:2020/4/12 15:46
 */
@Entity
@Table(name = "menu")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "name")
    private String name;
}
