package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.in.ReturnHistoryCreateInDTO;
import com.zhengyuanfang.dto.out.ReturnHistoryListOutDTO;

import java.util.List;

public interface ReturnHistoryService {
    Long create(ReturnHistoryCreateInDTO returnHistoryCreateInDTO);

    List<ReturnHistoryListOutDTO> findAll(Integer returnId);
}
