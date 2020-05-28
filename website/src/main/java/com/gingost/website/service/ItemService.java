package com.gingost.website.service;

import com.gingost.website.domain.Evaluate;
import com.gingost.website.domain.Item;

import java.util.List;
import java.util.Map;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/316:58
 **/
public interface ItemService {

    List<Item> findAllItem(String id,int page);

    int getItemCount(String pid);

    Item getItemByid(Integer id);

    List getItemEvaluate(Integer itemId,int page,int size);

    Map getItemEvaluateCount(Integer itemId);

    int getFuzzyQueryItemCount(String itemName);

    List<Item> getFuzzyQueryItem(String itemName,int page);
}
