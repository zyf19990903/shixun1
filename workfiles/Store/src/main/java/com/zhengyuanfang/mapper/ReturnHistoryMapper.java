package com.zhengyuanfang.mapper;

import com.zhengyuanfang.po.ReturnHistory;

import java.util.List;

public interface ReturnHistoryMapper {
    int deleteByPrimaryKey(Long returnHistoryId);

    int insert(ReturnHistory record);

    int insertSelective(ReturnHistory record);

    ReturnHistory selectByPrimaryKey(Long returnHistoryId);

    int updateByPrimaryKeySelective(ReturnHistory record);

    int updateByPrimaryKey(ReturnHistory record);

    List<ReturnHistory> findByReturnId(Integer returnId);
}