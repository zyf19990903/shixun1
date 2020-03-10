package com.zhengyuanfang.service;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.in.ProductCreateInDTO;
import com.zhengyuanfang.dto.in.ProductSearchInDTO;
import com.zhengyuanfang.dto.in.ProductUpdateInDTO;
import com.zhengyuanfang.dto.out.ProductListOutDTO;
import com.zhengyuanfang.dto.out.ProductShowOutDTO;

import java.util.List;

public interface ProductService {
    Page<ProductListOutDTO> findAll(Integer pageNum, ProductSearchInDTO productSearchInDTO);

    Integer create(ProductCreateInDTO productCreateInDTO);

    void update(ProductUpdateInDTO productUpdateInDTO);

    ProductShowOutDTO show(Integer productId);

    void delete(Integer productId);

    void batchDelete(List<Integer> productIds);
}
