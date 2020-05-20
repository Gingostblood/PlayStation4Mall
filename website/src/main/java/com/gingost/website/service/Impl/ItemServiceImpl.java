package com.gingost.website.service.Impl;

import com.gingost.website.dao.EvaluateDao;
import com.gingost.website.dao.ItemDao;
import com.gingost.website.domain.Evaluate;
import com.gingost.website.domain.Item;
import com.gingost.website.service.ItemService;
import com.gingost.website.utils.PageUtils;
import com.sun.jmx.snmp.SnmpTooBigException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/316:58
 **/
@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private ItemDao itemDao;
    private EvaluateDao evaluateDao;
    @Override
    public List<Item> findAllItem(String pid,int page) {
        if(pid==null||pid.equals("")){
            return  itemDao.findAllItem(page);
        }else
            return itemDao.findAllItemByPid(Integer.parseInt(pid),page);

    }

    @Override
    public int getItemCount(String pid) {
        if(pid==null||pid.equals("")){
            return itemDao.getItemCount();
        }else
            return itemDao.getItemCountByPid(Integer.parseInt(pid));
    }

    @Override
    public Item getItemByid(Integer id) {
        return itemDao.getItemByid(id);
    }

    @Override
    public List getItemEvaluate(Integer itemId,int page,int size) {
        List<Evaluate> evaluateList = evaluateDao.findAllEvaluateByItemId(itemId);
        //DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       return PageUtils.toPage(page-1,size,evaluateList);
    }

    @Override
    public Map getItemEvaluateCount(Integer itemId) {
        List<Evaluate> evaluateList = evaluateDao.findAllEvaluateByItemId(itemId);
        BigDecimal b=BigDecimal.ZERO;
        for (Evaluate evaluate:evaluateList){
            b=b.add(BigDecimal.valueOf(evaluate.getStar()));
        }
        BigDecimal avg=b.divide(BigDecimal.valueOf(evaluateList.size()),1, RoundingMode.HALF_UP);
        Map<String,Object> map=new LinkedHashMap<>();
        map.put("avg",avg);
        map.put("size",evaluateList.size());
        return map;
    }
}
