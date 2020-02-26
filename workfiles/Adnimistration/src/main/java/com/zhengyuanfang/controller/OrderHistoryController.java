package com.zhengyuanfang.controller;

import com.zhengyuanfang.dto.in.OrderHistoryCreateInDTO;
import com.zhengyuanfang.dto.out.OrderHistoryListOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderhistory")
public class OrderHistoryController {

    /*
    * 查看当前订单的历史
    */
    @GetMapping("/getListByOrderId")
    public List<OrderHistoryListOutDTO> getListByOrderId(@RequestParam Long orderId){
        return null;
    }
    /*
     * 添加订单历史
     */
    @PostMapping("/create")
    public Integer create(@RequestBody OrderHistoryCreateInDTO orderHistoryCreateInDTO){
        return null;
    }

}
