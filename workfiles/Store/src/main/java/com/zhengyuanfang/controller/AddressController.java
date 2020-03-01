package com.zhengyuanfang.controller;

import com.zhengyuanfang.dto.in.AddressCreateInDTO;
import com.zhengyuanfang.dto.in.AddressUpdateInDTO;
import com.zhengyuanfang.dto.out.AddressListOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    /*
     *查询客户所有地址
     */
    @GetMapping("/getAddressByCustomerId")
    public List<AddressListOutDTO> getAddressByCustomerId(@RequestAttribute Integer customerId){
        return null;
    }

    /*
     *添加地址
     */
    @PostMapping("/create")
    public Integer create(@RequestBody AddressCreateInDTO addressCreateInDTO,
                          @RequestAttribute Integer customerId){
        return null;
    }

    /*
     *修改地址
     */
    @PostMapping("/update")
    public void update(@RequestBody AddressUpdateInDTO addressUpdateInDTO,
                       @RequestAttribute Integer customerId){
        
    }

}
