package com.zhengyuanfang.service;

import com.zhengyuanfang.po.ProductOperation;

import java.util.List;

public interface ProductOperationService {
    void count(Integer productId);

    List<ProductOperation> selectHotProduct();
}
