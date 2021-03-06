package com.zhengyuanfang.mapper;


import com.github.pagehelper.Page;
import com.zhengyuanfang.po.Return;

public interface ReturnMapper {
    int deleteByPrimaryKey(Integer returnId);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer returnId);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    Page<Return> findAll(Integer customerId);
}