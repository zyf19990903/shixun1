package com.zhengyuanfang.service;


import com.zhengyuanfang.dto.in.OrderSearchInDTO;
import com.zhengyuanfang.dto.out.OrderListOutDTO;
import com.zhengyuanfang.dto.out.OrderShowOutDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;

public interface OrderService {

    PageOutDTO<OrderListOutDTO> findAll(OrderSearchInDTO orderSearchInDTO, Integer pageNum);

    OrderShowOutDTO show(Long orderId);
}
