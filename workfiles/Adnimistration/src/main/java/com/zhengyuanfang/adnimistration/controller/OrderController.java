package com.zhengyuanfang.adnimistration.controller;

import com.zhengyuanfang.adnimistration.dto.in.OrderSearchInDTO;
import com.zhengyuanfang.adnimistration.dto.out.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    /*
     *模糊分查询所有订单
     */
    @GetMapping("/list")
    public PageOutDTO<OrderListOutDTO> search(OrderSearchInDTO orderSearchInDTO,
                                              @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        return null;
    }

    /*
     *显示订单
     */
    @GetMapping("/show")
    public OrderShowOutDTO getById(@RequestParam Long orderId){
        return null;
    }

    /*
     *显示当前订单的发票信息
     */
    @GetMapping("/getInvoiceInfo")
    public OrderInvoiceShowOutDTO getInvoiceInfo(@RequestParam Long orderId){
        return null;
    }

    /*
     *查看当前订单的运输费用信息
     */
    @GetMapping("/getShipInfo")
    public OrderShipShowOutDTO getShipInfo(@RequestParam Long orderId){
        return null;
    }
}
