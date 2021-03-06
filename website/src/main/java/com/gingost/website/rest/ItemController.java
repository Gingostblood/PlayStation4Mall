package com.gingost.website.rest;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/42:53
 **/

@RestController
@RequestMapping("/item/")
@AllArgsConstructor
@CrossOrigin
public class ItemController {
    private ItemService itemService;

    @GetMapping("findAllItem")
    public ResponseEntity findAllItem(String pid, Integer page) {
        return new ResponseEntity(itemService.findAllItem(pid, (page - 1) * 24));
    }

    @GetMapping("getItemCount")
    public int getItemCount(String pid) {
        return itemService.getItemCount(pid);
    }

    @GetMapping("getItemByid")
    public ResponseEntity getItemByid(Integer id) {
        return new ResponseEntity(itemService.getItemByid(id));
    }

    @GetMapping("getItemEvaluate")
    public ResponseEntity getItemEvaluate(Integer itemId, int page, int size) {
        return new ResponseEntity(itemService.getItemEvaluate(itemId, page, size));
    }

    @GetMapping("getItemEvaluateCount")
    public ResponseEntity getItemEvaluateCount(Integer itemId) {
        return new ResponseEntity(itemService.getItemEvaluateCount(itemId));
    }

    @GetMapping("getFuzzyQueryItemCount")
    public int getFuzzyQueryItemCount(String itemName){
        return itemService.getFuzzyQueryItemCount(itemName);
    }

    @GetMapping("getFuzzyQueryItem")
    public ResponseEntity getFuzzyQueryItem(String itemName,int page){
        return new ResponseEntity(itemService.getFuzzyQueryItem(itemName,(page - 1) * 24));
    }
}
