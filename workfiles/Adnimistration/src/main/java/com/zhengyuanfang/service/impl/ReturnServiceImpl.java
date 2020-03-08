package com.zhengyuanfang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengyuanfang.dto.in.ReturnUpdateActionInDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ReturnListOutDTO;
import com.zhengyuanfang.dto.out.ReturnShowOutDTO;
import com.zhengyuanfang.mapper.ReturnMapper;
import com.zhengyuanfang.po.Return;
import com.zhengyuanfang.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public ReturnShowOutDTO getById(Integer returnId) {
        Return returns = returnMapper.selectByPrimaryKey(returnId);

        ReturnShowOutDTO returnShowOutDTO = new ReturnShowOutDTO();

        returnShowOutDTO.setReturnId(returns.getReturnId());
        returnShowOutDTO.setOrderId(returns.getOrderId());
        returnShowOutDTO.setOrderTimestamp(returns.getOrderTime().getTime());
        returnShowOutDTO.setCustomerId(returns.getCustomerId());
        returnShowOutDTO.setCustomerName(returns.getCustomerName());
        returnShowOutDTO.setMobile(returns.getMobile());
        returnShowOutDTO.setEmail(returns.getEmail());
        returnShowOutDTO.setStatus(returns.getStatus());
        returnShowOutDTO.setAction(returns.getAction());
        returnShowOutDTO.setProductCode(returns.getProductCode());
        returnShowOutDTO.setProductName(returns.getProductName());
        returnShowOutDTO.setQuantity(returns.getQuantity());
        returnShowOutDTO.setReason(returns.getReason());
        returnShowOutDTO.setOpened(returns.getOpened());
        returnShowOutDTO.setComment(returns.getComment());
        returnShowOutDTO.setCreateTimestamp(returns.getCreateTime().getTime());
        returnShowOutDTO.setUpdateTimestamp(returns.getUpdateTime().getTime());

        return returnShowOutDTO;
    }

    @Override
    public void update(ReturnUpdateActionInDTO returnUpdateActionInDTO) {
        Return returns = new Return();
        returns.setReturnId(returnUpdateActionInDTO.getReturnId());
        returns.setAction(returnUpdateActionInDTO.getAction());
        returns.setUpdateTime(new Date());

        int i = returnMapper.updateByPrimaryKeySelective(returns);
    }
}
