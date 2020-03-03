package com.zhengyuanfang.service;

import com.github.pagehelper.Page;
import com.zhengyuanfang.po.Administrator;

import java.util.List;

public interface AdministratorService {

    Administrator getByUsername(String username);

    Administrator getById(Integer administratorId);

    void update(Administrator administrator);

    Page<Administrator> findAll(Integer pageNum);

    Integer create(Administrator administrator);

    void delete(Integer adminstratorId);

    void batchDelete(List<Integer> administratorIds);
}
