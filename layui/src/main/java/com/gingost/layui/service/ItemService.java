package com.gingost.layui.service;

import com.gingost.layui.domain.Item;
import com.gingost.layui.domain.dto.QueryCriteria.ItemQueryCriteria;
import com.gingost.layui.domain.dto.resp.ItemRespDto;
import com.gingost.layui.domain.vo.LayuiTableVo;


/**
 * @author:lezzy
 * @Date:2020/4/2 10:33
 */
public interface ItemService {
    LayuiTableVo<ItemRespDto> findAll(int page, int size);

    void saveItem(Item item);

    void changeStatu(Integer id);

    void deleteItemById(Integer id);

    void deleteItemByIds(ItemQueryCriteria req);
}
