package com.zhengyuanfang.controller;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.in.CustomerSearchInDTO;
import com.zhengyuanfang.dto.in.CustomerSetStatusInDTO;
import com.zhengyuanfang.dto.out.CustomerListOutDTO;
import com.zhengyuanfang.dto.out.CustomerShowOutDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.po.Address;
import com.zhengyuanfang.po.Customer;
import com.zhengyuanfang.service.AddressService;
import com.zhengyuanfang.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    /*
     *模糊分查询所有的客户
     */
    @GetMapping("/list")
    public PageOutDTO<CustomerListOutDTO> findAll(CustomerSearchInDTO customerSearchInDTO,
                                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        PageOutDTO<CustomerListOutDTO> pageOutDTO = customerService.findAll(pageNum);
        return pageOutDTO;
    }

    /*
     *查看用户信息
     */
    @GetMapping("/show")
    public CustomerShowOutDTO getById(@RequestParam Integer customerId){
        CustomerShowOutDTO customerShowOutDTO = customerService.getById(customerId);
        return customerShowOutDTO;
    }

    /*
     *禁止(设置)用户状态
     */
    @PostMapping("/setStatus")
    public void setStatus(@RequestBody CustomerSetStatusInDTO customerSetStatusInDTO){
        customerService.setStatus(customerSetStatusInDTO);
    }
}
