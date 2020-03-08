package com.zhengyuanfang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengyuanfang.dto.in.ReturnCreateActionInDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ReturnListOutDTO;
import com.zhengyuanfang.enumeration.ReturnStatus;
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
    public Integer create(ReturnCreateActionInDTO returnCreateActionInDTO, Integer customerId) {

        Return returns = new Return();
        returns.setOrderId(returnCreateActionInDTO.getOrderId());
        returns.setOrderTime(new Date(returnCreateActionInDTO.getOrderTimestamp()));
        returns.setCustomerId(customerId);
        returns.setCustomerName(returnCreateActionInDTO.getCustomerName());
        returns.setMobile(returnCreateActionInDTO.getMobile());
        returns.setEmail(returnCreateActionInDTO.getEmail());
        returns.setStatus((byte) ReturnStatus.ToProcess.ordinal());
        returns.setProductCode(returnCreateActionInDTO.getProductCode());
        returns.setProductName(returnCreateActionInDTO.getProductName());
        returns.setQuantity(returnCreateActionInDTO.getQuantity());
        returns.setReason(returnCreateActionInDTO.getReason());
        returns.setOpened(returnCreateActionInDTO.getOpened());
        returns.setComment(returnCreateActionInDTO.getComment());
        Date now = new Date();
        returns.setCreateTime(now);
        returns.setUpdateTime(now);

        returnMapper.insertSelective(returns);

        Integer returnId = returns.getReturnId();

        return returnId;
    }

    @Override
    public PageOutDTO<ReturnListOutDTO> findAll(Integer pageNum, Integer customerId) {
        PageHelper.startPage(pageNum,5);
        Page<Return> page = returnMapper.findAll(customerId);

        List<ReturnListOutDTO> returnListOutDTOS = page.stream().map(aReturn -> {
            ReturnListOutDTO returnListOutDTO = new ReturnListOutDTO();
            returnListOutDTO.setReturnId(aReturn.getReturnId());
            returnListOutDTO.setOrderId(aReturn.getOrderId());
            returnListOutDTO.setCustomerId(aReturn.getCustomerId());
            returnListOutDTO.setCustomerName(aReturn.getCustomerName());
            returnListOutDTO.setStatus(aReturn.getStatus());
            returnListOutDTO.setCreateTimestamp(aReturn.getCreateTime().getTime());
            return returnListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<ReturnListOutDTO> pageOutDTO = new PageOutDTO<>();

        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setList(returnListOutDTOS);

        return pageOutDTO;
    }
}
