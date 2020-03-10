package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.in.CustomerRegisterInDto;
import com.zhengyuanfang.dto.in.CustomerResetPwdInDTO;
import com.zhengyuanfang.exception.ClientException;
import com.zhengyuanfang.po.Customer;

import java.util.HashMap;

public interface CustomerService {
    Integer register(CustomerRegisterInDto customerRegisterInDTO);

    Customer getByUsername(String username);

    Customer getById(Integer customerId);

    void update(Customer customer);

    String getByEmail(String email) throws ClientException;

    void restPwd(CustomerResetPwdInDTO customerResetPwdInDTO, HashMap<String, String> emailPwdResetCodeMap) throws ClientException;
}
