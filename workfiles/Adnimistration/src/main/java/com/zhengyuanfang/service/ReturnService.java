package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.in.ReturnSearchInDTO;
import com.zhengyuanfang.dto.in.ReturnUpdateActionInDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ReturnListOutDTO;
import com.zhengyuanfang.dto.out.ReturnShowOutDTO;

public interface ReturnService {
    PageOutDTO<ReturnListOutDTO> findAll(Integer pageNum, ReturnSearchInDTO returnSearchInDTO);

    ReturnShowOutDTO getById(Integer returnId);

    void update(ReturnUpdateActionInDTO returnUpdateActionInDTO);
}
