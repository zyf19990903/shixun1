package com.zhengyuanfang.mapper;

import com.github.pagehelper.Page;
import com.zhengyuanfang.po.Administrator;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer administratorId);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(Integer administratorId);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);

    Administrator selectByUsername(String username);

    Page<Administrator> findAll();

    void batchDelete(@Param("administratorIds") List<Integer> administratorIds);

    Administrator selectByEmail(String email);
}