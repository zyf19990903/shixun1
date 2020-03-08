package com.zhengyuanfang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ReturnListOutDTO;
import com.zhengyuanfang.mapper.ReturnMapper;
import com.zhengyuanfang.po.Return;
import com.zhengyuanfang.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    private ReturnMapper returnMapper;

    @Override
    public PageOutDTO<ReturnListOutDTO> findAll(Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        Page<Return> page = returnMapper.findAll();

        List<ReturnListOutDTO> returnListOutDTOS = page.stream().map(returns ->{
            ReturnListOutDTO returnListOutDTO = new ReturnListOutDTO();
            returnListOutDTO.setReturnId(returns.getReturnId());
            returnListOutDTO.setOrderId(returns.getOrderId());
            returnListOutDTO.setCustomerId(returns.getCustomerId());
            returnListOutDTO.setCustomerName(returns.getCustomerName());
            returnListOutDTO.setProductCode(returns.getProductCode());
            returnListOutDTO.setProductName(returns.getProductName());
            returnListOutDTO.setStatus(returns.getStatus());
            returnListOutDTO.setCreateTimestamp(returns.getCreateTime().getTime());
            returnListOutDTO.setUpdateTimestamp(returns.getUpdateTime().getTime());

            return returnListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<ReturnListOutDTO> pageOutDTO = new PageOutDTO<>();

        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setList(returnListOutDTOS);
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageNum(page.getPageNum());

        return pageOutDTO;
    }
}
