package com.gingost.website.service.Impl;

import com.gingost.website.dao.ItemDao;
import com.gingost.website.domain.Item;
import com.gingost.website.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/316:58
 **/
@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private ItemDao itemDao;
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
}
