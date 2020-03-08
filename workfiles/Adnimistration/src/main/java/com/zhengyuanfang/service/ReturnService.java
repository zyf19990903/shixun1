package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ReturnListOutDTO;

public interface ReturnService {
    PageOutDTO<ReturnListOutDTO> findAll(Integer pageNum);
}
