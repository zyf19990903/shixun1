package com.zhengyuanfang.store.controller;

import com.zhengyuanfang.store.dto.in.CustomerLoginInDTO;
import com.zhengyuanfang.store.dto.out.CustomerRegisterOutDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerStoreController {
    @GetMapping("/login")
    public String login(CustomerLoginInDTO customerLoginInDTO){
        return null;
    }
    @PostMapping("/register")
    public Integer register(CustomerRegisterOutDto customerRegisterOutDto){
        return 0;
    }
}
