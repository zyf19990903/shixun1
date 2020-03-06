package com.zhengyuanfang.service;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.in.CustomerSetStatusInDTO;
import com.zhengyuanfang.dto.out.CustomerListOutDTO;
import com.zhengyuanfang.dto.out.CustomerShowOutDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.po.Customer;

public interface CustomerService {
    PageOutDTO<CustomerListOutDTO> findAll(Integer pageNum);

    CustomerShowOutDTO getById(Integer customerId);

    void setStatus(CustomerSetStatusInDTO customerSetStatusInDTO);
}
