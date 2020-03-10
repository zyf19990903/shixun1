package com.zhengyuanfang.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.zhengyuanfang.constant.ClientExceptionConstant;
import com.zhengyuanfang.dto.in.*;
import com.zhengyuanfang.dto.out.CustomerGetProfileOutDTO;
import com.zhengyuanfang.dto.out.CustomerLoginOutDTO;
import com.zhengyuanfang.exception.ClientException;
import com.zhengyuanfang.po.Customer;
import com.zhengyuanfang.service.CustomerService;
import com.zhengyuanfang.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.HashMap;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerStoreController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JWTUtil jwtUtil;

    private HashMap<String, String> emailPwdResetCodeMap = new HashMap();

    /*
     *用户登录
     */
    @GetMapping("/login")
    public CustomerLoginOutDTO login(CustomerLoginInDTO customerLoginInDTO) throws ClientException {
        //查找是否存在当前用户
        Customer customer = customerService.getByUsername(customerLoginInDTO.getUsername());
        if(customer == null){
            throw new ClientException(ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRCODE, ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRMSG);
        }
        String encPwdDb = customer.getEncryptedPassword();
        //BCrypt加密
        BCrypt.Result result = BCrypt.verifyer().verify(customerLoginInDTO.getPassword().toCharArray(), encPwdDb);
        if(result.verified){//验证成功
            CustomerLoginOutDTO customerLoginOutDTO = jwtUtil.issueToken(customer);
            return customerLoginOutDTO;
        }else {
            throw new ClientException(ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRCODE, ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRMSG);
        }
    }

    /*
    *用户注册
    */
    @PostMapping("/register")
    public Integer register(@RequestBody CustomerRegisterInDto customerRegisterInDTO){
        Integer customerId = customerService.register(customerRegisterInDTO);
        return customerId;
    }

    /*
     *用户面板信息
     */
    @GetMapping("/getProfile")
    public CustomerGetProfileOutDTO getProfile(@RequestAttribute Integer customerId){
        Customer customer = customerService.getById(customerId);
        CustomerGetProfileOutDTO customerGetProfileOutDTO = new CustomerGetProfileOutDTO();
        customerGetProfileOutDTO.setCustomerId(customer.getCustomerId());
        customerGetProfileOutDTO.setUsername(customer.getUsername());
        customerGetProfileOutDTO.setRealName(customer.getRealName());
        customerGetProfileOutDTO.setMobile(customer.getMobile());
        customerGetProfileOutDTO.setMobileVerified(customer.getMobileVerified());
        customerGetProfileOutDTO.setEmail(customer.getEmail());
        customerGetProfileOutDTO.setEmailVerified(customer.getEmailVerified());
        return customerGetProfileOutDTO;
    }

    /*
     *修改用户信息
     */
    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody CustomerUpdateProfileInDTO customerUpdateProfileInDTO,
                              @RequestAttribute Integer customerId){
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setRealName(customerUpdateProfileInDTO.getRealName());
        customer.setMobile(customerUpdateProfileInDTO.getMobile());
        customer.setEmail(customerUpdateProfileInDTO.getEmail());
        customerService.update(customer);
    }

    /*
     *用户修改密码
     */
    @PostMapping("/changePwd")
    public void changePwd(@RequestBody CustomerChangePwdInDTO customerChangePwdInDTO,
                          @RequestAttribute Integer customerId) throws ClientException {
        Customer customer = customerService.getById(customerId);
        String encPwdDB = customer.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(customerChangePwdInDTO.getOriginPwd().toCharArray(), encPwdDB);

        if (result.verified) {
            String newPwd = customerChangePwdInDTO.getNewPwd();
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, newPwd.toCharArray());
            customer.setEncryptedPassword(bcryptHashString);
            customerService.update(customer);
        }else {
            throw new ClientException(ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRCODE, ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRMSG);
        }
    }

    /*
     *用户获取重置代码
     */
    @GetMapping("/getPwdResetCode")
    public void getPwdResetCode(@RequestParam String email) throws ClientException {
        //获取重置码
        String hex = customerService.getByEmail(email);
        emailPwdResetCodeMap.put(email, hex);
    }

    /*
     *用户重置密码
     */
    @PostMapping("/resetPwd")
    public void resetPwd(@RequestBody CustomerResetPwdInDTO customerResetPwdInDTO) throws ClientException {
        customerService.restPwd(customerResetPwdInDTO,emailPwdResetCodeMap);
    }

}
