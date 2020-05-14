package com.gingost.layui.service;

import com.gingost.layui.domain.vo.ItemEchartsBarVo;
import com.gingost.layui.domain.vo.ItemEchartsPieVo;

import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/4/16 16:14
 */
public interface EchartsService {
    ItemEchartsBarVo toBar(Integer id);

    List<ItemEchartsPieVo> toPie();
}
