package com.zhengyuanfang.mapper;

import com.zhengyuanfang.po.ProductOperation;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductOperationMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(ProductOperation record);

    int insertSelective(ProductOperation record);

    ProductOperation selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(ProductOperation record);

    int updateByPrimaryKey(ProductOperation record);

//    custom

    List<ProductOperation> selectHotProduct();

}