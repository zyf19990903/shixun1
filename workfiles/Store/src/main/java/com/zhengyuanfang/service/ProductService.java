package com.zhengyuanfang.service;


import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.in.ProductSearchInDTO;
import com.zhengyuanfang.dto.out.ProductListOutDTO;
import com.zhengyuanfang.dto.out.ProductShowOutDTO;
import com.zhengyuanfang.po.Product;

public interface ProductService {

    ProductShowOutDTO show(Integer productId);

    Page<ProductListOutDTO> findAll(ProductSearchInDTO productSearchInDTO, Integer pageNum);

    Product getById(Integer productId);
}
