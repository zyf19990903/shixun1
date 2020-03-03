package com.zhengyuanfang.controller;

import com.zhengyuanfang.dto.in.AddressCreateInDTO;
import com.zhengyuanfang.dto.in.AddressUpdateInDTO;
import com.zhengyuanfang.dto.out.AddressListOutDTO;
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
     *查询客户所有地址
     */
    @GetMapping("/getAddressByCustomerId")
    public List<AddressListOutDTO> getAddressByCustomerId(@RequestAttribute Integer customerId){
        List<Address> addresses = addressService.getByCustomerId(customerId);

        List<AddressListOutDTO> addressListOutDTOS = addresses.stream().map(address -> {
            AddressListOutDTO addressListOutDTO = new AddressListOutDTO();
            addressListOutDTO.setAddressId(address.getAddressId());
            addressListOutDTO.setContent(address.getContent());
            addressListOutDTO.setReceiverName(address.getReceiverName());
            addressListOutDTO.setReceiverMobile(address.getReceiverMobile());
            addressListOutDTO.setContent(address.getContent());
            return addressListOutDTO;
        }).collect(Collectors.toList());

        return addressListOutDTOS;
    }

    /*
     *添加地址
     */
    @PostMapping("/create")
    public Integer create(@RequestBody AddressCreateInDTO addressCreateInDTO,
                          @RequestAttribute Integer customerId){
        Address address = new Address();
        address.setCustomerId(customerId);
        address.setTag(addressCreateInDTO.getTag());
        address.setReceiverName(addressCreateInDTO.getReceiverName());
        address.setReceiverMobile(addressCreateInDTO.getReceiverMobile());
        address.setContent(addressCreateInDTO.getContent());

        addressService.create(address);
        Integer addressId = address.getAddressId();
        return addressId;
    }

    /*
     *修改地址
     */
    @PostMapping("/update")
    public void update(@RequestBody AddressUpdateInDTO addressUpdateInDTO,
                       @RequestAttribute Integer customerId){
        Address address = new Address();
        address.setAddressId(addressUpdateInDTO.getAddressId());
        address.setTag(addressUpdateInDTO.getTag());
        address.setReceiverName(addressUpdateInDTO.getReceiverName());
        address.setReceiverMobile(addressUpdateInDTO.getReceiverMobile());
        address.setContent(addressUpdateInDTO.getContent());
        addressService.update(address);
    }

    /*
    * 删除地址
    */
    @PostMapping("/delete")
    public void delete(@RequestBody Integer addressId){
        addressService.delete(addressId);
    }
}
