package com.zhengyuanfang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengyuanfang.mapper.AdministratorMapper;
import com.zhengyuanfang.po.Administrator;
import com.zhengyuanfang.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator getByUsername(String username) {
        Administrator administrator = administratorMapper.selectByUsername(username);
        return administrator;
    }

    @Override
    public Administrator getById(Integer administratorId) {
        Administrator administrator = administratorMapper.selectByPrimaryKey(administratorId);
        return administrator;
    }

    @Override
    public void update(Administrator administrator) {
        administratorMapper.updateByPrimaryKeySelective(administrator);
    }

    @Override
    public Page<Administrator> findAll(Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        Page<Administrator> page = administratorMapper.findAll();
        return page;
    }

    @Override
    public Integer create(Administrator administrator) {
        administratorMapper.insertSelective(administrator);
        Integer administratorId = administrator.getAdministratorId();
        return administratorId;
    }

    @Override
    public void delete(Integer adminstratorId) {
        administratorMapper.deleteByPrimaryKey(adminstratorId);
    }

    @Override
    public void batchDelete(List<Integer> administratorIds) {
        administratorMapper.batchDelete(administratorIds);
    }
}
