package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.in.CustomerRegisterInDto;

public interface CustomerService {
    Integer register(CustomerRegisterInDto customerRegisterInDTO);
}
