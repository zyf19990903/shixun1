package com.zhengyuanfang.service.impl;

import com.zhengyuanfang.dto.in.OrderHistoryCreateInDTO;
import com.zhengyuanfang.dto.out.OrderHistoryListOutDTO;
import com.zhengyuanfang.mapper.OrderHistoryMapper;
import com.zhengyuanfang.po.OrderHistory;
import com.zhengyuanfang.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryMapper orderHistoryMapper;

    @Override
    public Long insert(OrderHistoryCreateInDTO orderHistoryCreateInDTO) {

        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrderId(orderHistoryCreateInDTO.getOrderId());
        orderHistory.setTime(new Date());
        orderHistory.setOrderStatus(orderHistoryCreateInDTO.getOrderStatus());
        orderHistory.setComment(orderHistoryCreateInDTO.getComment());
        orderHistory.setCustomerNotified(orderHistoryCreateInDTO.getCustomerNotified());

        orderHistoryMapper.insertSelective(orderHistory);

        Long id = orderHistory.getOrderHistoryId();
        return id;
    }

    @Override
    public List<OrderHistoryListOutDTO> findByOrederId(Long orderId) {

        List<OrderHistory> orderHistories = orderHistoryMapper.getByOrderId(orderId);
        List<OrderHistoryListOutDTO> orderHistoryListOutDTOS = orderHistories.stream().map(orderHistory -> {
            OrderHistoryListOutDTO orderHistoryListOutDTO = new OrderHistoryListOutDTO();
            orderHistoryListOutDTO.setOrderHistoryId(orderHistory.getOrderHistoryId());
            orderHistoryListOutDTO.setTimestamp(orderHistory.getTime().getTime());
            orderHistoryListOutDTO.setOrderStatus(orderHistory.getOrderStatus());
            orderHistoryListOutDTO.setComment(orderHistory.getComment());
            orderHistoryListOutDTO.setCustomerNotified(orderHistory.getCustomerNotified());
            return orderHistoryListOutDTO;
        }).collect(Collectors.toList());
        return orderHistoryListOutDTOS;
    }
}
