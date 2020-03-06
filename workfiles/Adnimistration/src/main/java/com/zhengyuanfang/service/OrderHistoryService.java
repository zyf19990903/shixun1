package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.in.OrderHistoryCreateInDTO;

public interface OrderHistoryService {
    Long insert(OrderHistoryCreateInDTO orderHistoryCreateInDTO);
}
