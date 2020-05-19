package com.gingost.website.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/5/19 16:27
 */
public class PageUtils {

    //list分页
    public static List toPage(int page, int size , List list) {
        int fromIndex = page * size;
        int toIndex = page * size + size;
        if(fromIndex > list.size()){
            return new ArrayList();
        } else if(toIndex >= list.size()) {
            return list.subList(fromIndex,list.size());
        } else {
            return list.subList(fromIndex,toIndex);
        }
    }
}
