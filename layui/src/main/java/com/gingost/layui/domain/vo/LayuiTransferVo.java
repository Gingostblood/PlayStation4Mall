package com.gingost.layui.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author:lezzy
 * @Date:2020/4/22 17:34
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayuiTransferVo {
    private List<Map<String,Object>> leftData;
    private List<Integer> rightData;
}
