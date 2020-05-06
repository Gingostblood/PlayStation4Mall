package com.gingost.layui.service.impl;

import com.gingost.layui.domain.Image;
import com.gingost.layui.repository.ImageJpa;
import com.gingost.layui.service.ImageService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @Author:lezzy
 * @Date:2020/4/6 17:14
 */
@Service
@Data
public class ImageServiceImpl implements ImageService {
    private final static String localUrl = "E:/ps4ItemImage/";
    private final static String nginxURL = "http://www.lezzyImage.com/";
    private ImageJpa imageJpa;

    public ImageServiceImpl(ImageJpa imageJpa) {
        this.imageJpa = imageJpa;
    }

    @Override
    public Map uploadImage(MultipartFile uploadFile, String typeInterface) {
        Integer height;
        Integer weight;
        try {
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            height = bufferedImage.getHeight();
            weight = bufferedImage.getWidth();
            if (!typeInterface.equals("lezzy")) {
                if (height == weight) {
                    throw new RuntimeException("请选择正确的缩略图");
                }
            } else {
                if (height == 0 || weight == 0) {
                    throw new RuntimeException("请选择正确的图片");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("哎呀，文件走丢了呢");
        }
        String imageName = uploadFile.getOriginalFilename();
        imageName = imageName.toLowerCase();
        String datePath = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
        String realPath = localUrl + datePath;
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();
        int beginIndex = imageName.lastIndexOf(".");
        String type = imageName.substring(beginIndex);
        String uuidName = uuid + type;
        String httpUrl = nginxURL + datePath + uuidName;
        Image image = new Image(height, weight, httpUrl);
        try {
            uploadFile.transferTo(new File(realPath + uuidName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("哎呀，服务器文件损坏了呢");
        }
        Map<String, Object> map = new HashMap<>();
        Map<String, String> dataMap = new HashMap<>();
        if (!typeInterface.equals("lezzy")) {
            dataMap.put("url", image.getUrl());
            map.put("code", 0);
            map.put("msg", "上传成功");
            map.put("data", dataMap);
        } else {
            dataMap.put("src", image.getUrl());
            map.put("code", 0);
            map.put("msg", "上传成功");
            map.put("data", dataMap);
        }
        return map;
    }
}
