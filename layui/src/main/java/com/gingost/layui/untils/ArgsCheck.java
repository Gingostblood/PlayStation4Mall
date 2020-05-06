package com.gingost.layui.untils;

import java.util.Collection;
import java.util.Collections;

/**
 * @Author:lezzy
 * @Date:2020/5/1 12:59
 */
public class ArgsCheck {

    public static void StringCheck(String str,String msg){
        if(str==null||str.equals("")){
            throw new RuntimeException(msg);
        }
    }

    public static void NumCheck(Integer num,String msg){
        if (num<0||num==null){
            throw new RuntimeException(msg);
        }
    }

    public static void CollectionCheck(Collection collection,String msg){
        if (collection==null||collection.size()==0){
            throw new RuntimeException(msg);
        }
    }

    public static void ObjCheck(Object obj,String msg){
        if(obj==null){
            throw new RuntimeException(msg);
        }
    }
}
