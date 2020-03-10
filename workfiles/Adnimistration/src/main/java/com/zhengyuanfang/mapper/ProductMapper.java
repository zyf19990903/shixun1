package com.zhengyuanfang.mapper;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.out.ProductListOutDTO;
import com.zhengyuanfang.po.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Page<ProductListOutDTO> findAll(@Param("productCode") String productCode,
                                    @Param("status") Byte status,
                                    @Param("stockQuantity") Integer stockQuantity,
                                    @Param("price") Double price,
                                    @Param("productName") String productName);
    void batchDelete(List<Integer> productIds);
}