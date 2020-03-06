package com.zhengyuanfang.service;


import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.out.OrderListOutDTO;
import com.zhengyuanfang.dto.out.OrderShowOutDTO;

public interface OrderService {

    Page<OrderListOutDTO> findAll(Integer pageNum);

    OrderShowOutDTO show(Long orderId);
}
