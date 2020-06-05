package com.gingost.layui.service.impl;

import com.gingost.layui.domain.vo.ItemEchartsBarVo;
import com.gingost.layui.domain.vo.ItemEchartsPieVo;
import com.gingost.layui.repository.ItemJpa;
import com.gingost.layui.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/4/16 16:14
 */
@Service
public class EchartsServiceImpl implements EchartsService {
    @Autowired
    private ItemJpa itemJpa;

    @Override
    //@Cacheable("itemBar")
    public ItemEchartsBarVo toBar(Integer id) {
        ItemEchartsBarVo itemEchartsBarVo =new ItemEchartsBarVo();
        List<Object[]> objectArrayList = itemJpa.findItemEchartsVo(id);
        List<String> name=new ArrayList<>();
        List<String> sale=new ArrayList<>();
        for (Object[] obj:objectArrayList){
            name.add(String.valueOf(obj[0]));
            sale.add(String.valueOf(obj[1]));
        }
        itemEchartsBarVo.setName(name);
        itemEchartsBarVo.setSales(sale);
        return itemEchartsBarVo;
    }

    @Override
    //@Cacheable("itemPie")
    public List<ItemEchartsPieVo> toPie() {
        List<Object[]> objectArrayList = itemJpa.findItemEchartsVo();
        List<ItemEchartsPieVo> itemEchartsPieVoList=new ArrayList<>();
        for (Object[] obj:objectArrayList){
            ItemEchartsPieVo itemEchartsPieVo=new ItemEchartsPieVo();
            itemEchartsPieVo.setName(String.valueOf(obj[0]));
            itemEchartsPieVo.setValue(Integer.parseInt(String.valueOf(obj[1])));
            itemEchartsPieVoList.add(itemEchartsPieVo);
        }
        return itemEchartsPieVoList;
    }
}
