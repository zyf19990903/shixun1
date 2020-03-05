package com.zhengyuanfang.service;


import com.zhengyuanfang.po.Address;

import java.util.List;

public interface AddressService {

    Address getById(Integer addressId);

    List<Address> getByCustomerId(Integer customerId);

}
