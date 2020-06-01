package com.gingost.layui.service.impl;

import com.gingost.layui.domain.Item;
import com.gingost.layui.domain.dto.QueryCriteria.ItemQueryCriteria;
import com.gingost.layui.domain.dto.resp.ItemRespDto;
import com.gingost.layui.domain.vo.LayuiTableVo;
import com.gingost.layui.mapper.ItemMapper;
import com.gingost.layui.repository.ItemJpa;
import com.gingost.layui.repository.MenuJpa;
import com.gingost.layui.service.ItemService;
import com.gingost.layui.untils.QueryHelp;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/4/2 10:33
 */
@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    private MenuJpa menuJpa;
    private ItemJpa itemJpa;
    private ItemMapper itemMapper;

    //查询商品列表
    @Override
    //@Cacheable(value = "items")
    public LayuiTableVo<ItemRespDto> findAll(int page, int size) {
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creatTime"));
        Page<Item> itemPage = itemJpa.findAll(pageable);
        List<Item> itemList = itemPage.getContent();
        List<ItemRespDto> itemRespDtoList = itemMapper.toDto(itemList);
        for (ItemRespDto itemRespDto : itemRespDtoList) {
            itemRespDto.setMenuName(menuJpa.findNameById(itemRespDto.getCId()));
        }
        int count = (int) itemPage.getTotalElements();
        if (count == 0) {
            throw new RuntimeException("未找到相关数据");
        }
        return new LayuiTableVo(count, itemRespDtoList);
    }

    //新增商品
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    //@CacheEvict(value = "items",allEntries = true)
    public void saveItem(Item item) {
        try {
            item.setCreatTime(new Date());
            item.setUpdateTime(item.getCreatTime());
            item.setStatu(1);
            item.setItemSales(0);
            itemJpa.save(item);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发生了不可预知的错误，请重试");
        }

    }

    //上下架
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    //@CacheEvict(value = "items",allEntries = true)
    public void changeStatu(Integer id) {
        Item item = itemJpa.findById(id).orElseGet(Item::new);
        int statu = item.getStatu();
        if (item.getStatu() == 1) {
            item.setStatu(0);
            item.setUpdateTime(new Date());
        } else {
            item.setStatu(1);
            item.setUpdateTime(new Date());
        }
        itemJpa.save(item);
    }

    //删除商品
    @Override
    @Transactional
    //@CacheEvict(value = "items",allEntries = true)
    public void deleteItemById(Integer id) {
        itemJpa.deleteById(id);
    }

    //批量删除商品
    @Override
    @Transactional
   // @CacheEvict(value = "items",allEntries = true)
    public void deleteItemByIds(ItemQueryCriteria req) {
        List<Item> list = itemJpa.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, req, criteriaBuilder));
        itemJpa.deleteAll(list);
    }

    @Override
    public Item findItemById(Integer id) {
        Item item = itemJpa.findById(id).orElseGet(Item::new);
        System.out.println(item);
        return item;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeItem(Item item) {
        Item i = itemJpa.findById(item.getId()).orElseGet(Item::new);
        i.setItemStock(item.getItemStock());
        i.setCId(item.getCId());
        i.setItemInfo(item.getItemInfo());
        i.setItemLogo(item.getItemLogo());
        i.setItemName(item.getItemName());
        i.setItemPrice(item.getItemPrice());
        i.setUpdateTime(new Date());
        try {
            itemJpa.save(i);
        } catch (Exception e) {
            throw  new RuntimeException("操作失败");
        }
    }
}
