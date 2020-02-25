package com.zhengyuanfang.adnimistration.controller;

import com.zhengyuanfang.adnimistration.dto.in.*;
import com.zhengyuanfang.adnimistration.dto.out.AdministratorGetProfileOutDTO;
import com.zhengyuanfang.adnimistration.dto.out.AdministratorListOutDTO;
import com.zhengyuanfang.adnimistration.dto.out.AdministratorShowOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    /*
    *管理员登录
    */
    @GetMapping("/login")
    public String login(AdministratorLoginInDTO administratorLoginInDTO){
        return null;
    }

    /*
     *获取管理员信息
     */

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(Integer adminstratorId){
        return null;
    }

    /*
     *修改管理员信息
     */
    @PostMapping("/updateProdfile")
    public void updateProdfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO){

    }

    /*
     *管理员得到重置代码
     */
    @GetMapping("/getPwdResetCode")
    public String getPwdResetCode(@RequestParam String email){
        return null;
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
    public List<AdministratorListOutDTO> findAll(Integer pageNum, Integer pageSize){
        return null;
    }

    /*
     *显示管理员信息
     */
    @GetMapping("/show")
    public AdministratorShowOutDTO show(Integer administratorId){
        return null;
    }

    /*
     *创建管理员
     */
    @PostMapping("/create")
    public Integer create(AdministratorCreateInDTO administratorCreateInDTO){
        return 0;
    }

    /*
     *修改管理员
     */
    @PostMapping("/update")
    public void update(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO){
    }

    /*
     *删除管理员
     */
    @PostMapping("/delete")
    public void delete(@RequestBody Integer adminstratorId){

    }

    /*
     *批量删除管理员
     */
    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> administratorIds){

    }
}
