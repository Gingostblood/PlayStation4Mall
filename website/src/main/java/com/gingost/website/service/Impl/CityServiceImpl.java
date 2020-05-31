package com.gingost.website.service.Impl;

import com.gingost.website.dao.CityDao;
import com.gingost.website.domain.City;
import com.gingost.website.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/3117:12
 **/

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private CityDao cityDao;
    @Override
    public List<City> findCitysByPid(Integer pid) {
        return cityDao.findCityByPid(pid);
    }
}
