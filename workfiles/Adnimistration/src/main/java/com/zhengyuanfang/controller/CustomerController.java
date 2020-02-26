package com.zhengyuanfang.controller;

import com.zhengyuanfang.dto.in.CustomerSearchInDTO;
import com.zhengyuanfang.dto.out.CustomerListOutDTO;
import com.zhengyuanfang.dto.out.CustomerShowOutDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    /*
     *模糊分查询所有的客户
     */
    @GetMapping("/list")
    public PageOutDTO<CustomerListOutDTO> search(CustomerSearchInDTO customerSearchInDTO,
                                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        return null;
    }

    /*
     *查看用户信息
     */
    @GetMapping("/show")
    public CustomerShowOutDTO getById(@RequestParam Integer customerId){
        return null;
    }

    /*
     *禁止用户
     */
    @PostMapping("/disable")
    public void disable(@RequestParam Integer customerId){

    }
}
