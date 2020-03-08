package com.zhengyuanfang.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengyuanfang.dto.in.ReturnCreateActionInDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ReturnHistoryListOutDTO;
import com.zhengyuanfang.dto.out.ReturnListOutDTO;
import com.zhengyuanfang.dto.out.ReturnShowOutDTO;
import com.zhengyuanfang.enumeration.ReturnStatus;
import com.zhengyuanfang.mapper.ReturnHistoryMapper;
import com.zhengyuanfang.mapper.ReturnMapper;
import com.zhengyuanfang.po.Return;
import com.zhengyuanfang.po.ReturnHistory;
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
    @Autowired
    private ReturnHistoryMapper returnHistoryMapper;

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

        List<ReturnListOutDTO> returnListOutDTOS = page.stream().map(returns -> {
            ReturnListOutDTO returnListOutDTO = new ReturnListOutDTO();
            returnListOutDTO.setReturnId(returns.getReturnId());
            returnListOutDTO.setOrderId(returns.getOrderId());
            returnListOutDTO.setCustomerId(returns.getCustomerId());
            returnListOutDTO.setCustomerName(returns.getCustomerName());
            returnListOutDTO.setStatus(returns.getStatus());
            returnListOutDTO.setCreateTimestamp(returns.getCreateTime().getTime());
            return returnListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<ReturnListOutDTO> pageOutDTO = new PageOutDTO<>();

        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setList(returnListOutDTOS);

        return pageOutDTO;
    }

    @Override
    public ReturnShowOutDTO show(Integer returnId) {
        Return returns = returnMapper.selectByPrimaryKey(returnId);

        ReturnShowOutDTO returnShowOutDTO = new ReturnShowOutDTO();
        returnShowOutDTO.setReturnId(returns.getReturnId());
        returnShowOutDTO.setOrderId(returns.getOrderId());
        returnShowOutDTO.setOrderTimestamp(returns.getOrderTime().getTime());
        returnShowOutDTO.setCustomerName(returns.getCustomerName());
        returnShowOutDTO.setMobile(returns.getMobile());
        returnShowOutDTO.setEmail(returns.getEmail());
        returnShowOutDTO.setStatus(returns.getStatus());
        returnShowOutDTO.setAction(returns.getAction());
        returnShowOutDTO.setProductCode(returns.getProductCode());
        returnShowOutDTO.setProductName(returns.getProductName());
        returnShowOutDTO.setQuantity(returns.getQuantity());
        returnShowOutDTO.setReason(returns.getReason());
        returnShowOutDTO.setComment(returns.getComment());
        returnShowOutDTO.setOpened(returns.getOpened());
        returnShowOutDTO.setCreateTimestamp(returns.getCreateTime().getTime());
        returnShowOutDTO.setUpdateTimestamp(returns.getUpdateTime().getTime());

        //查询此商品所有的退货历史
        List<ReturnHistory> returnHistories = returnHistoryMapper.findByReturnId(returnId);

        List<ReturnHistoryListOutDTO> returnHistoryListOutDTOS = returnHistories.stream().map(returnHistory -> {
            ReturnHistoryListOutDTO returnHistoryListOutDTO = new ReturnHistoryListOutDTO();
            returnHistoryListOutDTO.setTimestamp(returnHistory.getTime().getTime());
            returnHistoryListOutDTO.setReturnStatus(returnHistory.getReturnStatus());
            returnHistoryListOutDTO.setComment(returnHistory.getComment());
            return returnHistoryListOutDTO;
        }).collect(Collectors.toList());

        returnShowOutDTO.setReturnHistories(returnHistoryListOutDTOS);

        return returnShowOutDTO;
    }
}
