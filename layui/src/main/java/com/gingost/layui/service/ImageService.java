package com.gingost.layui.service;


import com.gingost.layui.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author:lezzy
 * @Date:2020/4/6 17:12
 */
public interface ImageService {
    Map uploadImage(MultipartFile uploadFile, String file);

    Map uploadRush(MultipartFile uploadFile);

    void rest();

    List<Image> findAllImage();
}
