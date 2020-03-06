package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.in.OrderHistoryCreateInDTO;
import com.zhengyuanfang.dto.out.OrderHistoryListOutDTO;

import java.util.List;

public interface OrderHistoryService {
    Long insert(OrderHistoryCreateInDTO orderHistoryCreateInDTO);

    List<OrderHistoryListOutDTO> findByOrederId(Long orderId);
}
