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
        Page<Customer> page = customerService.findAll(pageNum);
        List<CustomerListOutDTO> customerListOutDTOS = page.stream().map(customer -> {
            CustomerListOutDTO customerListOutDTO = new CustomerListOutDTO();
            customerListOutDTO.setCustomerId(customer.getCustomerId());
            customerListOutDTO.setUsername(customer.getUsername());
            customerListOutDTO.setRealName(customer.getRealName());
            customerListOutDTO.setMobile(customer.getMobile());
            customerListOutDTO.setEmail(customer.getEmail());
            customerListOutDTO.setStatus(customer.getStatus());
            customerListOutDTO.setCreateTimestamp(customer.getCreateTime().getTime());
            return customerListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<CustomerListOutDTO> pageOutDTO = new PageOutDTO<>();

        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setList(customerListOutDTOS);

        return pageOutDTO;
    }

    /*
     *查看用户信息
     */
    @GetMapping("/show")
    public CustomerShowOutDTO getById(@RequestParam Integer customerId){
        Customer customer = customerService.getById(customerId);

        CustomerShowOutDTO customerShowOutDTO = new CustomerShowOutDTO();
        customerShowOutDTO.setCustomerId(customerId);
        customerShowOutDTO.setUsername(customer.getUsername());
        customerShowOutDTO.setRealName(customer.getRealName());
        customerShowOutDTO.setMobile(customer.getMobile());
        customerShowOutDTO.setEmail(customer.getEmail());
        customerShowOutDTO.setAvatarUrl(customer.getAvatarUrl());
        customerShowOutDTO.setStatus(customer.getStatus());
        customerShowOutDTO.setRewordPoints(customer.getRewordPoints());
        customerShowOutDTO.setNewsSubscribed(customer.getNewsSubscribed());
        customerShowOutDTO.setCreateTimestamp(customer.getCreateTime().getTime());
        customerShowOutDTO.setDefaultAddressId(customer.getDefaultAddressId());

        Address defaultAddress = addressService.getById(customer.getDefaultAddressId());
        if (defaultAddress != null){
            customerShowOutDTO.setDefaultAddress(defaultAddress.getContent());
        }

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
