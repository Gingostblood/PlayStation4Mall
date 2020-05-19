package com.gingost.layui.rest;

import com.gingost.layui.annotation.Log;
import com.gingost.layui.common.ResponseEntity;
import com.gingost.layui.domain.Item;
import com.gingost.layui.domain.dto.QueryCriteria.ItemQueryCriteria;
import com.gingost.layui.domain.dto.resp.ItemRespDto;
import com.gingost.layui.domain.vo.LayuiTableVo;
import com.gingost.layui.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author:lezzy
 * @Date:2020/4/2 10:01
 */
@RestController
@Secured({"ROLE_ITEM","ROLE_ADMIN"})
@RequestMapping("/item/")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Log("查询商品数据")
    @GetMapping("findAll")
    public LayuiTableVo<ItemRespDto> findAll(int page, int size) {
        return itemService.findAll(page, size);
    }

    @Log("新增商品")
    @PostMapping("saveItem")
    public ResponseEntity saveItem(Item item) {
        itemService.saveItem(item);
        return new ResponseEntity("保存成功");
    }
    @Log("商品上/架")
    @PutMapping("changeStatu")
    public ResponseEntity changeStatu(Integer id) {
        itemService.changeStatu(id);
        return new ResponseEntity("操作成功");
    }

    @Log("单个商品删除(真)")
    @DeleteMapping("deleteItemById")
    public ResponseEntity deleteItemById(Integer id) {
        itemService.deleteItemById(id);
        return new ResponseEntity("删除成功");
    }

    @Log("批量商品删除(真)")
    @DeleteMapping("deleteItemByIds")
    public ResponseEntity deleteItemByIds(ItemQueryCriteria req) {
        itemService.deleteItemByIds(req);
        return new ResponseEntity("批量删除成功");
    }
}
