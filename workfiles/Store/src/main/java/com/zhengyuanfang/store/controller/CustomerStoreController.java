package com.zhengyuanfang.store.controller;

import com.zhengyuanfang.store.dto.in.*;
import com.zhengyuanfang.store.dto.out.CustomerGetProfileOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerStoreController {

    /*
     *用户登录
     */
    @GetMapping("/login")
    public String login(CustomerLoginInDTO customerLoginInDTO){
        return null;
    }

    /*
    *用户注册
    */
    @PostMapping("/register")
    public Integer register(CustomerRegisterInDto customerRegisterOutDto){
        return 0;
    }

    /*
     *用户面板信息
     */
    @GetMapping("/getProfile")
    public CustomerGetProfileOutDTO getProfile(@RequestAttribute Integer customerId){
        return null;
    }

    /*
     *修改用户信息
     */
    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody CustomerUpdateProfileInDTO customerUpdateProfileInDTO,
                              @RequestAttribute Integer customerId){

    }

    /*
     *用户修改密码
     */
    @PostMapping("/changePwd")
    public void changePwd(@RequestBody CustomerChangePwdInDTO customerChangePwdInDTO,
                          @RequestAttribute Integer customerId){

    }

    /*
     *用户获取重置代码
     */
    @GetMapping("/getPwdResetCode")
    public String getPwdResetCode(@RequestParam String email){
        return null;
    }

    /*
     *用户重置密码
     */
    @PostMapping("/resetPwd")
    public void resetPwd(@RequestBody CustomerResetPwdInDTO customerResetPwdInDTO){

    }

}
