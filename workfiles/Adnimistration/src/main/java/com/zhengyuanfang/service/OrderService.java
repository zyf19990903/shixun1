package com.zhengyuanfang.service;


import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.out.OrderListOutDTO;
import com.zhengyuanfang.dto.out.OrderShowOutDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;

public interface OrderService {

    PageOutDTO<OrderListOutDTO> findAll(Integer pageNum);

    OrderShowOutDTO show(Long orderId);
}
