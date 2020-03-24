package com.zhengyuanfang.mapper;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.out.ProductListOutDTO;
import com.zhengyuanfang.po.Product;
import org.apache.ibatis.annotations.Param;

;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Page<ProductListOutDTO> findAll(@Param("keyword") String keyword, @Param("status")byte status);
}