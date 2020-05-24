package com.gingost.website.service.Impl;

import com.gingost.website.dao.ImageDao;
import com.gingost.website.domain.Images;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/2422:18
 **/

@Service
@AllArgsConstructor
public class ImagesServiceImpl {
    private ImageDao imageDao;

    public List<Images> findAllImages(){
        return imageDao.findAll();
    }
}
