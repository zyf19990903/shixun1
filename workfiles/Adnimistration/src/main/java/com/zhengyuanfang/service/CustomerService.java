package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.in.CustomerSearchInDTO;
import com.zhengyuanfang.dto.in.CustomerSetStatusInDTO;
import com.zhengyuanfang.dto.out.CustomerListOutDTO;
import com.zhengyuanfang.dto.out.CustomerShowOutDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;

public interface CustomerService {
    PageOutDTO<CustomerListOutDTO> findAll(Integer pageNum, CustomerSearchInDTO customerSearchInDTO);

    CustomerShowOutDTO getById(Integer customerId);

    void setStatus(CustomerSetStatusInDTO customerSetStatusInDTO);
}
