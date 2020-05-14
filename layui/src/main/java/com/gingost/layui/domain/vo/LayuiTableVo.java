package com.gingost.layui.domain.vo;

import com.gingost.layui.common.ResponseEntity;
import lombok.Data;

import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/4/2 11:51
 */
@Data
public class LayuiTableVo<T> extends ResponseEntity {
    private int count;
    List<T> list;

    public LayuiTableVo(int count, List<T> list) {
        this.count = count;
        this.list = list;
    }
}
