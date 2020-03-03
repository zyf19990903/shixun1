package com.zhengyuanfang.service;

import com.zhengyuanfang.po.Administrator;

public interface AdministratorService {

    Administrator getByUsername(String username);

    Administrator getById(Integer administratorId);

    void update(Administrator administrator);
}
