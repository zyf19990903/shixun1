package com.zhengyuanfang.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengyuanfang.dto.out.OrderListOutDTO;
import com.zhengyuanfang.dto.out.OrderShowOutDTO;
import com.zhengyuanfang.mapper.CustomerMapper;
import com.zhengyuanfang.mapper.OrderDetailMapper;
import com.zhengyuanfang.mapper.OrderMapper;
import com.zhengyuanfang.po.Customer;
import com.zhengyuanfang.po.Order;
import com.zhengyuanfang.po.OrderDetail;
import com.zhengyuanfang.service.OrderService;
import com.zhengyuanfang.vo.OrderProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Page<OrderListOutDTO> findAll(Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        Page<OrderListOutDTO> page = orderMapper.findAll();
        return page;
    }

    @Override
    public OrderShowOutDTO show(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(orderId);

        OrderShowOutDTO orderShowOutDTO = new OrderShowOutDTO();

        orderShowOutDTO.setOrderId(orderId);
        orderShowOutDTO.setCustomerId(order.getCustomerId());
        Customer customer = customerMapper.selectByPrimaryKey(order.getCustomerId());
        orderShowOutDTO.setCustomerName(customer.getRealName());
        orderShowOutDTO.setStatus(order.getStatus());
        orderShowOutDTO.setTotalPrice(order.getTotalPrice());
        orderShowOutDTO.setRewordPoints(order.getRewordPoints());
        orderShowOutDTO.setCreateTimestamp(order.getCreateTime().getTime());
        orderShowOutDTO.setUpdateTimestamp(order.getUpdateTime().getTime());

        orderShowOutDTO.setShipMethod(orderDetail.getShipMethod());
        orderShowOutDTO.setShipAddress(orderDetail.getShipAddress());
        orderShowOutDTO.setShipPrice(orderDetail.getShipPrice());
        orderShowOutDTO.setPayMethod(orderDetail.getPayMethod());
        orderShowOutDTO.setInvoiceAddress(orderDetail.getInvoiceAddress());
        orderShowOutDTO.setInvoicePrice(orderDetail.getInvoicePrice());
        orderShowOutDTO.setComment(orderDetail.getComment());

        List<OrderProductVO> orderProductVOS = JSON.parseArray(orderDetail.getOrderProducts(), OrderProductVO.class);
        orderShowOutDTO.setOrderProducts(orderProductVOS);

        return orderShowOutDTO;
    }
}
