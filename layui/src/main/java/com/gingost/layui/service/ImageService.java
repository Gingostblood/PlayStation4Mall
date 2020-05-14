package com.gingost.layui.service;


import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author:lezzy
 * @Date:2020/4/6 17:12
 */
public interface ImageService {
    Map uploadImage(MultipartFile uploadFile, String file);
}
