package com.zhengyuanfang.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.zhengyuanfang.constant.ClientExceptionConstant;
import com.zhengyuanfang.dto.in.CustomerRegisterInDto;
import com.zhengyuanfang.enumeration.CustomerStatus;
import com.zhengyuanfang.exception.ClientException;
import com.zhengyuanfang.mapper.CustomerMapper;
import com.zhengyuanfang.po.Customer;
import com.zhengyuanfang.service.CustomerService;
import com.zhengyuanfang.util.JWTUtil;
import com.zhengyuanfang.util.MailBean;
import com.zhengyuanfang.util.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
    private MailService mailService;

    @Override
    public Integer register(CustomerRegisterInDto customerRegisterInDTO) {

        Customer customer = new Customer();
        customer.setUsername(customerRegisterInDTO.getUsername());
        customer.setRealName(customerRegisterInDTO.getRealName());
        customer.setEmail(customerRegisterInDTO.getEmail());
        customer.setEmailVerified(false);
        customer.setMobile(customerRegisterInDTO.getMobile());
        customer.setMobileVerified(false);
        customer.setNewsSubscribed(customerRegisterInDTO.getNewsSubscribed());
        customer.setCreateTime(new Date());
        customer.setStatus((byte) CustomerStatus.Enable.ordinal());
        customer.setRewordPoints(0);

        //密码加密
        String password = customerRegisterInDTO.getPassword();
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        customer.setEncryptedPassword(bcryptHashString);

        customerMapper.insertSelective(customer);
        Integer customerId = customer.getCustomerId();

        return customerId;
    }

    @Override
    public Customer getByUsername(String username) {
        Customer customer = customerMapper.selectByUsername(username);
        return customer;
    }

    @Override
    public Customer getById(Integer customerId) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        return customer;
    }

    @Override
    public void update(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public String getByEmail(String email) throws ClientException {
        Customer customer = customerMapper.selectByEmail(email);
        if (customer == null) {
            throw new ClientException(ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRCODE, ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRMSG);
        }
    //生成随机重置码
        byte[] bytes = secureRandom.generateSeed(3);
        String hex = DatatypeConverter.printHexBinary(bytes);

        MailBean mailBean = new MailBean();
        mailBean.setReceiver(email);
        mailBean.setSubject("jcart管理端管理员密码重置");
        mailBean.setContent(hex);
        try {
            mailService.sendSimpleMail(mailBean);
            return hex;
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
    }
}
