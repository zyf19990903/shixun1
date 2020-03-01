package com.zhengyuanfang.service;


import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.out.ProductListOutDTO;
import com.zhengyuanfang.dto.out.ProductShowOutDTO;

public interface ProductService {

    ProductShowOutDTO show(Integer productId);

    Page<ProductListOutDTO> findAll(Integer pageNum);
}
