package com.zhengyuanfang.service.impl;

import com.zhengyuanfang.dto.in.ReturnCreateActionInDTO;
import com.zhengyuanfang.enumeration.ReturnStatus;
import com.zhengyuanfang.mapper.ReturnMapper;
import com.zhengyuanfang.po.Return;
import com.zhengyuanfang.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
