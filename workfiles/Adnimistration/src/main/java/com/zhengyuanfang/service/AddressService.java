package com.zhengyuanfang.service;


import com.zhengyuanfang.dto.out.AddressListOutDTO;
import com.zhengyuanfang.dto.out.AddressShowOutDTO;
import com.zhengyuanfang.po.Address;

import java.util.List;

public interface AddressService {

    AddressShowOutDTO getById(Integer addressId);

    List<AddressListOutDTO> getByCustomerId(Integer customerId);

}
