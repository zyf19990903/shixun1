package com.zhengyuanfang.service;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.in.OrderCheckoutInDTO;
import com.zhengyuanfang.dto.out.OrderShowOutDTO;
import com.zhengyuanfang.po.Order;

public interface OrderService {
    Page<Order> getByCustomerId(Integer pageNum, Integer customerId);

    OrderShowOutDTO show(Long orderId);

    Long checkout(OrderCheckoutInDTO orderCheckoutInDTO, Integer customerId);
}
