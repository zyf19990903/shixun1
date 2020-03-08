package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.in.ReturnCreateActionInDTO;

public interface ReturnService {
    Integer create(ReturnCreateActionInDTO returnCreateActionInDTO, Integer customerId);
}
