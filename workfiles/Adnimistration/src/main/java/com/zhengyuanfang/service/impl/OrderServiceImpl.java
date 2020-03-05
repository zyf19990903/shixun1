package com.zhengyuanfang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengyuanfang.dto.out.OrderListOutDTO;
import com.zhengyuanfang.mapper.OrderDetailMapper;
import com.zhengyuanfang.mapper.OrderMapper;
import com.zhengyuanfang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public Page<OrderListOutDTO> findAll(Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        Page<OrderListOutDTO> page = orderMapper.findAll();
        return page;
    }
}
