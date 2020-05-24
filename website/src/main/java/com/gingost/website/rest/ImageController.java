package com.gingost.website.rest;

import com.gingost.website.common.ResponseEntity;
import com.gingost.website.service.Impl.ImagesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/2422:32
 **/

@RestController
@RequestMapping("/image/")
@AllArgsConstructor
public class ImageController {
    private ImagesServiceImpl imagesService;

    @GetMapping("findRushImage")
    public ResponseEntity findRushImage(){
        return new ResponseEntity(imagesService.findAllImages());
    }
}
