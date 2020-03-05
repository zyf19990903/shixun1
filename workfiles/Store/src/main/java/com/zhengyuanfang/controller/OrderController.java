package com.zhengyuanfang.controller;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.in.OrderCheckoutInDTO;
import com.zhengyuanfang.dto.out.OrderListOutDTO;
import com.zhengyuanfang.dto.out.OrderShowOutDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.po.Order;
import com.zhengyuanfang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;


    /*
    * 选中订单
    */
    @PostMapping("/checkout")
    public Long checkout(@RequestBody OrderCheckoutInDTO orderCheckoutInDTO,
                         @RequestAttribute Integer customerId) {
        Long orderId = orderService.checkout(orderCheckoutInDTO, customerId);
        return orderId;
    }


    /**
     * 查看自己所有的订单
     */
    @GetMapping("/list")
    public PageOutDTO<OrderListOutDTO> search(@RequestAttribute Integer customerId, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<Order> page = orderService.getByCustomerId(pageNum,customerId);

        List<OrderListOutDTO> orderListOutDTOS = page.stream().map(order -> {
            OrderListOutDTO orderListOutDTO = new OrderListOutDTO();
            orderListOutDTO.setOrderId(order.getOrderId());
            orderListOutDTO.setStatus(order.getStatus());
            orderListOutDTO.setTotalPrice(order.getTotalPrice());
            orderListOutDTO.setCreateTimestamp(order.getCreateTime().getTime());
            return orderListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<OrderListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setList(orderListOutDTOS);

        return pageOutDTO;
    }

    /**
     * 查看订单详细信息
     */
    @GetMapping("/show")
    public OrderShowOutDTO show(@RequestParam Long orderId){
        OrderShowOutDTO orderShowOutDTO = orderService.show(orderId);
        return orderShowOutDTO;
    }


}
