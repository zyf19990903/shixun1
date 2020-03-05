package com.zhengyuanfang.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengyuanfang.dto.in.OrderCheckoutInDTO;
import com.zhengyuanfang.dto.in.OrderProductInDTO;
import com.zhengyuanfang.dto.out.OrderShowOutDTO;
import com.zhengyuanfang.enumeration.OrderStatus;
import com.zhengyuanfang.mapper.OrderDetailMapper;
import com.zhengyuanfang.mapper.OrderMapper;
import com.zhengyuanfang.po.Address;
import com.zhengyuanfang.po.Order;
import com.zhengyuanfang.po.OrderDetail;
import com.zhengyuanfang.po.Product;
import com.zhengyuanfang.service.AddressService;
import com.zhengyuanfang.service.OrderService;
import com.zhengyuanfang.service.ProductService;
import com.zhengyuanfang.vo.OrderProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private AddressService addressService;


    @Override
    public Page<Order> getByCustomerId(Integer pageNum, Integer customerId) {
        PageHelper.startPage(pageNum,10);
        Page<Order> page = orderMapper.selectByCustomerId(customerId);
        return page;
    }

    @Override
    public OrderShowOutDTO show(Long orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        OrderDetail orderDetail = orderDetailMapper.selectByPrimaryKey(orderId);

        OrderShowOutDTO orderShowOutDTO = new OrderShowOutDTO();
        orderShowOutDTO.setOrderId(orderId);
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


        return null;
    }

    @Override
    @Transactional
    public Long checkout(OrderCheckoutInDTO orderCheckoutInDTO, Integer customerId) {
        //所选订单的所有商品
        List<OrderProductInDTO> orderProductInDTOS = orderCheckoutInDTO.getOrderProducts();
        List<OrderProductVO> orderProductVOS = orderProductInDTOS.stream().map(orderProductInDTO -> {
            Product orderProduct = productService.getById(orderProductInDTO.getProductId());
            OrderProductVO orderProductVO = new OrderProductVO();
            orderProductVO.setProductId(orderProduct.getProductId());
            orderProductVO.setProductCode(orderProduct.getProductCode());
            orderProductVO.setProductName(orderProduct.getProductName());
            Integer quantity = orderProductInDTO.getQuantity();
            orderProductVO.setQuantity(quantity);
            Double unitPrice = orderProduct.getPrice() * orderProduct.getDiscount();
            orderProductVO.setUnitPrice(unitPrice);
            Double totalPrice = unitPrice * quantity;
            orderProductVO.setTotalPrice(totalPrice);
            Integer unitRewordPoints = orderProduct.getRewordPoints();
            orderProductVO.setUnitRewordPoints(unitRewordPoints);
            Integer totalRewordPoints = unitRewordPoints * quantity;
            orderProductVO.setTotalRewordPoints(totalRewordPoints);
            return orderProductVO;
        }).collect(Collectors.toList());

        //总价
        double allTotalPrice = orderProductVOS.stream().mapToDouble(p -> p.getTotalPrice()).sum();
        //积分
        int allTotalRewordPoints = orderProductVOS.stream().mapToInt(p -> p.getTotalRewordPoints()).sum();

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setStatus((byte) OrderStatus.ToProcess.ordinal());
        order.setTotalPrice(allTotalPrice);
        order.setRewordPoints(allTotalRewordPoints);
        Date now = new Date();
        order.setCreateTime(now);
        order.setUpdateTime(now);
        //添加进订单表
        orderMapper.insertSelective(order);

        Long orderId = order.getOrderId();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setShipMethod(orderCheckoutInDTO.getShipMethod());
        orderDetail.setShipPrice(5.0);
        Address shipAddress = addressService.getById(orderCheckoutInDTO.getShipAddressId());
        orderDetail.setShipAddress(shipAddress.getContent());

        orderDetail.setPayMethod(orderCheckoutInDTO.getPayMethod());
        orderDetail.setInvoicePrice(allTotalPrice);
        Address invoiceAddress = addressService.getById(orderCheckoutInDTO.getInvoiceAddressId());
        orderDetail.setInvoiceAddress(invoiceAddress.getContent());

        orderDetail.setComment(orderCheckoutInDTO.getComment());

        orderDetail.setOrderProducts(JSON.toJSONString(orderProductVOS));
        //添加进订单副表
        orderDetailMapper.insertSelective(orderDetail);
        return orderId;
    }
}
