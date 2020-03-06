package com.zhengyuanfang.controller;

import com.zhengyuanfang.dto.in.OrderHistoryCreateInDTO;
import com.zhengyuanfang.dto.out.OrderHistoryListOutDTO;
import com.zhengyuanfang.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderhistory")
@CrossOrigin
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;
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
    public Long create(@RequestBody OrderHistoryCreateInDTO orderHistoryCreateInDTO){
        Long orderHistoryId = orderHistoryService.insert(orderHistoryCreateInDTO);
        return orderHistoryId;
    }

}
