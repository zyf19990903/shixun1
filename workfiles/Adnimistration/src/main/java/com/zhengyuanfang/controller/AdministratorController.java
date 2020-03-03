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
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/administrator")
@CrossOrigin
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private JWTUtil jwtUtil;

    /*
    *管理员登录
    */
    @GetMapping("/login")
    public AdministratorLoginOutDTO login(AdministratorLoginInDTO administratorLoginInDTO) throws ClientException {
        Administrator administrator = administratorService.getByUsername(administratorLoginInDTO.getUsername());
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }
        String encPwdDB = administrator.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(administratorLoginInDTO.getPassword().toCharArray(), encPwdDB);

        if (result.verified) {
            AdministratorLoginOutDTO administratorLoginOutDTO = jwtUtil.issueToken(administrator);
            return administratorLoginOutDTO;
        }else {
            throw new ClientException(ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRCODE, ClientExceptionConstant.ADNINISTRATOR_PASSWORD_INVALID_ERRMSG);
        }
    }

    /*
     *获取管理员信息
     */

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(@RequestAttribute Integer administratorId){
        Administrator administrator = administratorService.getById(administratorId);
        AdministratorGetProfileOutDTO administratorGetProfileOutDTO = new AdministratorGetProfileOutDTO();
        administratorGetProfileOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorGetProfileOutDTO.setUsername(administrator.getUsername());
        administratorGetProfileOutDTO.setRealName(administrator.getRealName());
        administratorGetProfileOutDTO.setEmail(administrator.getEmail());
        administratorGetProfileOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorGetProfileOutDTO.setCreateTimestamp(administrator.getCreateTime().getTime());
        return administratorGetProfileOutDTO;
    }

    /*
     *修改管理员信息
     */
    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO,
                              @RequestAttribute Integer administratorId){
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorId);
        administrator.setRealName(administratorUpdateProfileInDTO.getRealName());
        administrator.setEmail(administratorUpdateProfileInDTO.getEmail());
        administrator.setAvatarUrl(administratorUpdateProfileInDTO.getAvatarUrl());
        administratorService.update(administrator);
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
    public PageOutDTO<AdministratorListOutDTO> findAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum){
        Page<Administrator> page = administratorService.findAll(pageNum);
        List<AdministratorListOutDTO> administratorListOutDTOS = page.stream().map(administrator -> {
            AdministratorListOutDTO administratorListOutDTO = new AdministratorListOutDTO();
            administratorListOutDTO.setAdministratorId(administrator.getAdministratorId());
            administratorListOutDTO.setUsername(administrator.getUsername());
            administratorListOutDTO.setRealName(administrator.getRealName());
            administratorListOutDTO.setStatus(administrator.getStatus());
            administratorListOutDTO.setCreateTimestamp(administrator.getCreateTime().getTime());
            return administratorListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<AdministratorListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setList(administratorListOutDTOS);
        return pageOutDTO;
    }

    /*
     *显示管理员信息
     */
    @GetMapping("/show")
    public AdministratorShowOutDTO show(@RequestParam Integer administratorId){
        Administrator administrator = administratorService.getById(administratorId);
        AdministratorShowOutDTO administratorShowOutDTO = new AdministratorShowOutDTO();
        administratorShowOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorShowOutDTO.setUsername(administrator.getUsername());
        administratorShowOutDTO.setRealName(administrator.getRealName());
        administratorShowOutDTO.setEmail(administrator.getEmail());
        administratorShowOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorShowOutDTO.setStatus(administrator.getStatus());
        return administratorShowOutDTO;
    }

    /*
     *创建管理员
     */
    @PostMapping("/create")
    public Integer create(@RequestBody AdministratorCreateInDTO administratorCreateInDTO){
        Administrator administrator = new Administrator();
        administrator.setUsername(administratorCreateInDTO.getUsername());
        administrator.setRealName(administratorCreateInDTO.getRealName());
        administrator.setEmail(administratorCreateInDTO.getEmail());
        administrator.setAvatarUrl(administratorCreateInDTO.getAvatarUrl());
        administrator.setStatus((byte) AdministratorStatus.Enable.ordinal());
        administrator.setCreateTime(new Date());

        String bcryptHashString = BCrypt.withDefaults().hashToString(12, administratorCreateInDTO.getPassword().toCharArray());
        administrator.setEncryptedPassword(bcryptHashString);

        Integer administratorId = administratorService.create(administrator);

        return administratorId;
    }

    /*
     *修改管理员
     */
    @PostMapping("/update")
    public void update(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO){
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorUpdateInDTO.getAdministratorId());
        administrator.setRealName(administratorUpdateInDTO.getRealName());
        administrator.setEmail(administratorUpdateInDTO.getEmail());
        administrator.setAvatarUrl(administratorUpdateInDTO.getAvatarUrl());
        administrator.setStatus(administratorUpdateInDTO.getStatus());
        String password = administratorUpdateInDTO.getPassword();
        if (password != null && !password.isEmpty()){
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            administrator.setEncryptedPassword(bcryptHashString);
        }
        administratorService.update(administrator);
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
