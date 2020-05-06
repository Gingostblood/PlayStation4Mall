package com.gingost.layui.untils;

import java.util.List;

/**
 * @author:lezzy
 * @Date:2020/4/2 10:11
 */
public interface BaseDE<D, E> {

    //将Entity转Dto
    D toDto(E entity);

    //将Dto转Entity
    E toEntity(D dto);

    //ListEntity转ListDto
    List<D> toDto(List<E> entityList);

    //ListDto转ListEntity
    List<E> toEntity(List<D> dtoList);
}
