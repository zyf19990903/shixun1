package com.zhengyuanfang.service;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.in.CustomerSetStatusInDTO;
import com.zhengyuanfang.po.Customer;

public interface CustomerService {
    Page<Customer> findAll(Integer pageNum);

    Customer getById(Integer customerId);

    void setStatus(CustomerSetStatusInDTO customerSetStatusInDTO);
}
