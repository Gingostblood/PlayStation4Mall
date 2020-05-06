package com.gingost.layui.test;

import com.gingost.layui.domain.Item;
import com.gingost.layui.domain.User;
import com.gingost.layui.domain.dto.QueryCriteria.ItemQueryCriteria;
import com.gingost.layui.domain.vo.ItemEchartsBarVo;
import com.gingost.layui.repository.ItemJpa;
import com.gingost.layui.repository.UserJpa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @Author:lezzy
 * @Date:2020/4/15 21:32
 */

@SpringBootTest
public class JpaTest {
    @Autowired
    private ItemJpa itemJpa;
    @Autowired
    private UserJpa userJpa;
    private Object all;


    @Test
    public void test() {
        //Item item = itemJpa.findById(3).orElseGet(Item::new);
        Optional<Item> nmsl = itemJpa.findById(3);
        System.out.println(nmsl);
    }

    @Test
    public void test2() {
        Set<Integer> set = new HashSet<>();
        set.add(4);
        set.add(5);
        set.add(6);
        ItemQueryCriteria itemQueryCriteria = new ItemQueryCriteria();
        itemQueryCriteria.setIds(set);
        System.out.println(itemQueryCriteria.getIds());
        //System.out.println(set.toString());
        /*Set<Integer> set=new HashSet<>();
        set.add(3);
        itemQueryDto.setIds(set);
        List<Item> all = itemJpa.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, itemQueryDto, criteriaBuilder));
        System.out.println(all);
        itemJpa.deleteAll(all);*/

    }

    @Test
    public void test3() {
        List<Object[]> list =itemJpa.findItemEchartsVo(1);
        //List<ItemEchartsDto> itemEchartsDtoList=new ArrayList<>();
        ItemEchartsBarVo itemEchartsBarVo =new ItemEchartsBarVo();
        List<String> name=new ArrayList<>();
        List<String> sales=new ArrayList<>();
        for (Object[] obj:list){
            //ItemEchartsDto itemEchartsDto=new ItemEchartsDto();
            //itemEchartsDto.setName(String.valueOf(obj[0]));
            //itemEchartsDto.setSales(Integer.valueOf(String.valueOf( obj[1])));
            //itemEchartsDto.setSales(Integer.parseInt(String.valueOf(obj[1])));
            //itemEchartsDtoList.add(itemEchartsDto);
            name.add(String.valueOf(obj[0]));
            sales.add(String.valueOf(obj[1]));

        }
        itemEchartsBarVo.setName(name);
        itemEchartsBarVo.setSales(sales);
        System.out.println(itemEchartsBarVo);
        /*List<Item> byCid = itemJpa.findByCid(3);
        System.out.println(byCid);*/
    }
    @Test
    public void test4(){
        Date date=new Date();
        System.out.println(date.getTime());
    }

    @Test
    public void test5(){
        userJpa.deleteById(3);
    }

    @Test
    public void test6(){
        User user1=new User();
        user1.setId(1);
        user1.setEmail("2233");
        User user2=new User();
        user2.setId(1);
        user2.setEmail("2233");
        System.out.println(user1.equals(user2)?"ok":"给爷爬");
    }
}
