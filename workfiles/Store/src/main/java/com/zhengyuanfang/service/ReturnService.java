package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.in.ReturnCreateActionInDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ReturnListOutDTO;

public interface ReturnService {
    Integer create(ReturnCreateActionInDTO returnCreateActionInDTO, Integer customerId);

    PageOutDTO<ReturnListOutDTO> findAll(Integer pageNum,Integer customerId);
}
