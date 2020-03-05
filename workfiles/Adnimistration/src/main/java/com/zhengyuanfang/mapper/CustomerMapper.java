package com.zhengyuanfang.mapper;

import com.github.pagehelper.Page;
import com.zhengyuanfang.po.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    Page<Customer> findAll();
}