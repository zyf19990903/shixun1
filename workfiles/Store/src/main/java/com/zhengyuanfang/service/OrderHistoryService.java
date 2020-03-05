package com.zhengyuanfang.service;


import com.zhengyuanfang.po.OrderHistory;

import java.util.List;

public interface OrderHistoryService {

    List<OrderHistory> getByOrderId(Long orderId);

}
