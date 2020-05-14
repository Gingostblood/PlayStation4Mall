package com.gingost.layui.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author:lezzy
 * @Date:2020/4/6 17:04
 */
@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    @Column(name = "height")
    @JsonIgnore
    private Integer height;
    @JsonIgnore
    @Column(name = "width")
    private Integer width;

    @Column(name = "url")
    private String url;

    public Image(Integer height, Integer width, String url) {
        this.height = height;
        this.width = width;
        this.url = url;
    }
}
