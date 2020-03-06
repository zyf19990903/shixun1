package com.zhengyuanfang.controller;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.in.OrderSearchInDTO;

import com.zhengyuanfang.dto.out.*;
import com.zhengyuanfang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*
     *模糊分查询所有订单
     */
    @GetMapping("/list")
    public PageOutDTO<OrderListOutDTO> search(OrderSearchInDTO orderSearchInDTO,
                                              @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        PageOutDTO<OrderListOutDTO> pageOutDTO = orderService.findAll(pageNum);
        return pageOutDTO;
    }

    /*
     *显示订单
     */
    @GetMapping("/show")
    public OrderShowOutDTO getById(@RequestParam Long orderId){
        OrderShowOutDTO orderShowOutDTO = orderService.show(orderId);
        return orderShowOutDTO;
    }

    /*
     *显示当前订单的发票信息
     */
    @GetMapping("/getInvoiceInfo")
    public OrderInvoiceShowOutDTO getInvoiceInfo(@RequestParam Long orderId){
        return null;
    }

    /*
     *查看当前订单的发货
     */
    @GetMapping("/getShipInfo")
    public OrderShipShowOutDTO getShipInfo(@RequestParam Long orderId){
        return null;
    }
}
