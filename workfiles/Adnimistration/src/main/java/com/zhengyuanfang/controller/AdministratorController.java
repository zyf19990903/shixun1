package com.zhengyuanfang.controller;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.pagehelper.Page;
import com.zhengyuanfang.constant.ClientExceptionConstant;
import com.zhengyuanfang.dto.in.*;
import com.zhengyuanfang.dto.out.*;
import com.zhengyuanfang.enumeration.AdministratorStatus;
import com.zhengyuanfang.exception.ClientException;
import com.zhengyuanfang.po.Administrator;
import com.zhengyuanfang.service.AdministratorService;
import com.zhengyuanfang.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/administrator")
@CrossOrigin
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    private Map<String, String> emailPwdResetCodeMap = new HashMap<>();

    /*
    *管理员登录
    */
    @GetMapping("/login")
    public AdministratorLoginOutDTO login(AdministratorLoginInDTO administratorLoginInDTO) throws ClientException {
        AdministratorLoginOutDTO administratorLoginOutDTO = administratorService.getByUsername(administratorLoginInDTO);
        return  administratorLoginOutDTO;
    }

    /*
     *获取管理员信息
     */

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(@RequestAttribute Integer administratorId){
        AdministratorGetProfileOutDTO administratorGetProfileOutDTO = administratorService.getById(administratorId);
        return administratorGetProfileOutDTO;
    }

    /*
     *修改管理员信息
     */
    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO,
                              @RequestAttribute Integer administratorId){
        administratorService.updateProfile(administratorUpdateProfileInDTO,administratorId);
    }

    /*
     *管理员得到重置代码
     */
    @GetMapping("/getPwdResetCode")
    public void getPwdResetCode(@RequestParam String email) throws ClientException {
        //获取重置码
        String hex = administratorService.getByEmail(email);
        emailPwdResetCodeMap.put(email, hex);
    }

    /*
     *管理员重置密码
     */
    @PostMapping("/resetPwd")
    public void resetPwd(@RequestBody AdministratorResetPwdInDTO administratorResetPwdInDTO){

    }

    /*
     *管理员列表
     */
    @GetMapping("/list")
    public PageOutDTO<AdministratorListOutDTO> findAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum){
        PageOutDTO<AdministratorListOutDTO> pageOutDTO = administratorService.findAll(pageNum);
        return pageOutDTO;
    }

    /*
     *显示管理员信息
     */
    @GetMapping("/show")
    public AdministratorShowOutDTO show(@RequestParam Integer administratorId){
        AdministratorShowOutDTO administratorShowOutDTO = administratorService.show(administratorId);
        return administratorShowOutDTO;
    }

    /*
     *创建管理员
     */
    @PostMapping("/create")
    public Integer create(@RequestBody AdministratorCreateInDTO administratorCreateInDTO){
        Integer administratorId = administratorService.create(administratorCreateInDTO);
        return administratorId;
    }

    /*
     *修改管理员
     */
    @PostMapping("/update")
    public void update(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO){
        administratorService.update(administratorUpdateInDTO);
    }

    /*
     *删除管理员
     */
    @PostMapping("/delete")
    public void delete(@RequestBody Integer adminstratorId){
        administratorService.delete(adminstratorId);
    }

    /*
     *批量删除管理员
     */
    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> administratorIds){
        administratorService.batchDelete(administratorIds);
    }
}
