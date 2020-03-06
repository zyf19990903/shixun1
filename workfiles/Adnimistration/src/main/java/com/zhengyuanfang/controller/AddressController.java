package com.zhengyuanfang.controller;

import com.zhengyuanfang.dto.out.AddressListOutDTO;
import com.zhengyuanfang.dto.out.AddressShowOutDTO;
import com.zhengyuanfang.po.Address;
import com.zhengyuanfang.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    /*
    *根据客户id查询地址
    */
    @GetMapping("/getListByCustomerId")
    public List<AddressListOutDTO> getListByCustomerId(@RequestParam Integer customerId){
        List<AddressListOutDTO> addressListOutDTOS = addressService.getByCustomerId(customerId);
        return addressListOutDTOS;
    }



    /*
     *根据id查询地址  默认
     */
    @GetMapping("/show")
    public AddressShowOutDTO getById(@RequestParam Integer addressId){
        AddressShowOutDTO addressShowOutDTO = addressService.getById(addressId);
        return addressShowOutDTO;
    }
}
