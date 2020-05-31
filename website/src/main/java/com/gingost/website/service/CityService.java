package com.gingost.website.service;

import com.gingost.website.domain.City;

import java.util.List;

/**
 * @author Lezzy
 * @description
 * @data 2020/5/3117:11
 **/
public interface CityService {

    List<City> findCitysByPid(Integer pid);

}
