package com.gingost.layui.repository;

import com.gingost.layui.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/4/2 10:34
 */
public interface ItemJpa extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item> {
    @Modifying
    @Query(value = "update item set statu=?2 where id=?1", nativeQuery = true)
    void changeStatu(Integer id, int statu);

    @Query(value="SELECT m.name,t.sales FROM (SELECT c_id,sum(item_sales) sales from item GROUP BY c_id) t,menu m WHERE m.id=t.c_id and m.pid=?1",nativeQuery = true)
    List<Object[]> findItemEchartsVo(Integer pid);

    @Query(value = "SELECT m.name,t.stock FROM (SELECT c_id,sum(item_stock) stock from item GROUP BY c_id) t,menu m WHERE m.id=t.c_id ",nativeQuery = true)
    List<Object[]> findItemEchartsVo();

    @Query(value="select * from item where c_id=?1",nativeQuery = true)
    List<Item> findByCid(Integer id);

    @Query(value = "select item_name from item where id=?1",nativeQuery = true)
    String findNameById(Integer id);
}
