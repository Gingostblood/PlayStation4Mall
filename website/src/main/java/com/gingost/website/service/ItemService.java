package com.gingost.website.service;

import com.gingost.website.domain.Item;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/316:58
 **/
public interface ItemService {

    List<Item> findAllItem(String id,int page);

    int getItemCount(String pid);

    Item getItemByid(Integer id);
}
