package com.zhengyuanfang.store.controller;

import com.zhengyuanfang.store.dto.out.OrderListOutDTO;
import com.zhengyuanfang.store.dto.out.OrderShowOutDTO;
import com.zhengyuanfang.store.dto.out.PageOutDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    /**
     * 查看自己所有的订单
     */
    @GetMapping("/list")
    public PageOutDTO<OrderListOutDTO> search(Integer customerid,@RequestParam(required = false,defaultValue = "1") Integer pageNum){
        return null;
    }

    /**
     * 查看订单详细信息
     */
    @GetMapping("/show")
    public OrderShowOutDTO getById(@RequestParam Long orderId){
        return null;
    }


}
