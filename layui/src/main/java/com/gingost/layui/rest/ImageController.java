package com.gingost.layui.rest;

import com.gingost.layui.annotation.Log;
import com.gingost.layui.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}
