package com.zhengyuanfang.service.impl;

import com.zhengyuanfang.dto.in.ReturnHistoryCreateInDTO;
import com.zhengyuanfang.dto.out.ReturnHistoryListOutDTO;
import com.zhengyuanfang.mapper.ReturnHistoryMapper;
import com.zhengyuanfang.mapper.ReturnMapper;
import com.zhengyuanfang.po.Return;
import com.zhengyuanfang.po.ReturnHistory;
import com.zhengyuanfang.service.ReturnHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReturnHistoryServiceImpl implements ReturnHistoryService {

    @Autowired
    private ReturnHistoryMapper returnHistoryMapper;

    @Autowired
    private ReturnMapper returnMapper;

    @Override
    @Transactional
    public Long create(ReturnHistoryCreateInDTO returnHistoryCreateInDTO) {
        ReturnHistory returnHistory = new ReturnHistory();

        returnHistory.setReturnId(returnHistoryCreateInDTO.getReturnId());
        returnHistory.setTime(new Date());
        returnHistory.setReturnStatus(returnHistoryCreateInDTO.getReturnStatus());
        returnHistory.setComment(returnHistoryCreateInDTO.getComment());
        Boolean customerNotified = returnHistoryCreateInDTO.getCustomerNotifeid();
        returnHistory.setCustomerNotified(customerNotified);

        returnHistoryMapper.insertSelective(returnHistory);

        Long returnHistoryId = returnHistory.getReturnHistoryId();

        Return returns = new Return();
        returns.setReturnId(returnHistory.getReturnId());
        returns.setUpdateTime(new Date());

        returnMapper.updateByPrimaryKeySelective(returns);

        if (customerNotified != null && customerNotified){
            //通知用户
        }


        return returnHistoryId;
    }

    @Override
    public List<ReturnHistoryListOutDTO> findAll(Integer returnId) {
        List<ReturnHistory> returnHistoryList = returnHistoryMapper.findAll(returnId);

        List<ReturnHistoryListOutDTO> returnHistoryListOutDTOS = returnHistoryList.stream().map(returnHistory -> {
            ReturnHistoryListOutDTO returnHistoryListOutDTO = new ReturnHistoryListOutDTO();
            returnHistoryListOutDTO.setReturnHistoryId(returnHistory.getReturnHistoryId());
            returnHistoryListOutDTO.setTimestamp(returnHistory.getTime().getTime());
            returnHistoryListOutDTO.setReturnStatus(returnHistory.getReturnStatus());
            returnHistoryListOutDTO.setComment(returnHistory.getComment());
            returnHistoryListOutDTO.setCustomerNotified(returnHistory.getCustomerNotified());
            return returnHistoryListOutDTO;
        }).collect(Collectors.toList());

        return returnHistoryListOutDTOS;
    }
}
