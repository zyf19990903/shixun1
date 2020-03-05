package com.zhengyuanfang.service;


import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.out.OrderListOutDTO;

public interface OrderService {

    Page<OrderListOutDTO> findAll(Integer pageNum);

}
