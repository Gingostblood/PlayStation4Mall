package com.gingost.layui.rest;

import com.gingost.layui.annotation.Log;
import com.gingost.layui.common.ResponseEntity;
import com.gingost.layui.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author:lezzy
 * @Date:2020/4/6 17:11
 */
@RestController
@RequestMapping("/")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Log("进行图片上传")
    @PostMapping("upload")
    public Map upload(MultipartFile file, @RequestParam(defaultValue = "lezzy") String type) {
        return imageService.uploadImage(file, type);
    }

    @Log("轮播图设定")
    @PostMapping("rush")
    public Map uploadRush(MultipartFile file){
        return imageService.uploadRush(file);
    }

    @Log("重置轮播图")
    @PutMapping("rest")
    public void rest(){
         imageService.rest();
    }

    @GetMapping("find")
    public ResponseEntity findAllImage(){
        return new ResponseEntity(imageService.findAllImage());
    }
}
