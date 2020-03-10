package com.zhengyuanfang.mapper;

import com.github.pagehelper.Page;
import com.zhengyuanfang.po.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    Page<Customer> findAll(@Param("email") String email,
                           @Param("mobile")String mobile,
                           @Param("realName")String realName,
                           @Param("status")Byte status,
                           @Param("username")String username);
}