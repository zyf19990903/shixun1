package com.zhengyuanfang.service;

import com.zhengyuanfang.po.Address;

import java.util.List;

public interface AddressService {
    Integer create(Address address);

    void update(Address address);

    void delete(Integer addressId);

    List<Address> getByCustomerId(Integer customerId);
}
