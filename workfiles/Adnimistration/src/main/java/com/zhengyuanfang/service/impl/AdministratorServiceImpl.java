package com.zhengyuanfang.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhengyuanfang.constant.ClientExceptionConstant;
import com.zhengyuanfang.dto.in.*;
import com.zhengyuanfang.dto.out.*;
import com.zhengyuanfang.enumeration.AdministratorStatus;
import com.zhengyuanfang.exception.ClientException;
import com.zhengyuanfang.mapper.AdministratorMapper;
import com.zhengyuanfang.po.Administrator;
import com.zhengyuanfang.service.AdministratorService;
import com.zhengyuanfang.util.JWTUtil;
import com.zhengyuanfang.util.MailBean;
import com.zhengyuanfang.util.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
    private MailService mailService;

    @Override
    public AdministratorLoginOutDTO getByUsername(AdministratorLoginInDTO administratorLoginInDTO) throws ClientException {
        Administrator administrator = administratorMapper.selectByUsername(administratorLoginInDTO.getUsername());
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }
        String encPwdDB = administrator.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(administratorLoginInDTO.getPassword().toCharArray(), encPwdDB);

        if (result.verified) {
            AdministratorLoginOutDTO administratorLoginOutDTO = jwtUtil.issueToken(administrator);
            return administratorLoginOutDTO;
        }else {
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PASSWORD_INVALID_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_PASSWORD_INVALID_ERRMSG);
        }
    }

    @Override
    public AdministratorGetProfileOutDTO getById(Integer administratorId) {
        Administrator administrator = administratorMapper.selectByPrimaryKey(administratorId);
        AdministratorGetProfileOutDTO administratorGetProfileOutDTO = new AdministratorGetProfileOutDTO();
        administratorGetProfileOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorGetProfileOutDTO.setUsername(administrator.getUsername());
        administratorGetProfileOutDTO.setRealName(administrator.getRealName());
        administratorGetProfileOutDTO.setEmail(administrator.getEmail());
        administratorGetProfileOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorGetProfileOutDTO.setCreateTimestamp(administrator.getCreateTime().getTime());
        return administratorGetProfileOutDTO;
    }

    @Override
    public void updateProfile(AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO, Integer administratorId) {
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorId);
        administrator.setRealName(administratorUpdateProfileInDTO.getRealName());
        administrator.setEmail(administratorUpdateProfileInDTO.getEmail());
        administrator.setAvatarUrl(administratorUpdateProfileInDTO.getAvatarUrl());
        administratorMapper.updateByPrimaryKeySelective(administrator);
    }

    @Override
    public PageOutDTO<AdministratorListOutDTO> findAll(Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        Page<Administrator> page = administratorMapper.findAll();
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

    @Override
    public Integer create(AdministratorCreateInDTO administratorCreateInDTO) {

        Administrator administrator = new Administrator();
        administrator.setUsername(administratorCreateInDTO.getUsername());
        administrator.setRealName(administratorCreateInDTO.getRealName());
        administrator.setEmail(administratorCreateInDTO.getEmail());
        administrator.setAvatarUrl(administratorCreateInDTO.getAvatarUrl());
        administrator.setStatus((byte) AdministratorStatus.Enable.ordinal());
        administrator.setCreateTime(new Date());

        String bcryptHashString = BCrypt.withDefaults().hashToString(12, administratorCreateInDTO.getPassword().toCharArray());
        administrator.setEncryptedPassword(bcryptHashString);

        administratorMapper.insertSelective(administrator);
        Integer administratorId = administrator.getAdministratorId();
        return administratorId;
    }

    @Override
    public void delete(Integer adminstratorId) {
        administratorMapper.deleteByPrimaryKey(adminstratorId);
    }

    @Override
    public void batchDelete(List<Integer> administratorIds) {
        administratorMapper.batchDelete(administratorIds);
    }

    @Override
    public AdministratorShowOutDTO show(Integer administratorId) {
        Administrator administrator = administratorMapper.selectByPrimaryKey(administratorId);

        AdministratorShowOutDTO administratorShowOutDTO = new AdministratorShowOutDTO();
        administratorShowOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorShowOutDTO.setUsername(administrator.getUsername());
        administratorShowOutDTO.setRealName(administrator.getRealName());
        administratorShowOutDTO.setEmail(administrator.getEmail());
        administratorShowOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorShowOutDTO.setStatus(administrator.getStatus());
        return administratorShowOutDTO;
    }

    @Override
    public void update(AdministratorUpdateInDTO administratorUpdateInDTO) {
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
        administratorMapper.updateByPrimaryKeySelective(administrator);
    }

    @Override
    public String getByEmail(String email) throws ClientException {
        Administrator administrator = administratorMapper.selectByEmail(email);
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRMSG);
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

    @Override
    public void restPwd(AdministratorResetPwdInDTO administratorResetPwdInDTO, Map<String, String> emailPwdResetCodeMap) throws ClientException {
        String email = administratorResetPwdInDTO.getEmail();
        if (email == null) {
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_EMAIL_NONE_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_PWDRESET_EMAIL_NONE_ERRMSG);
        }

        //获取邮箱里面的重置码
        String innerResetCode = emailPwdResetCodeMap.get(email);
        if (innerResetCode == null) {
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_INNER_RESETCODE_NONE_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_PWDRESET_INNER_RESETCODE_NONE_ERRMSG);
        }

        //获取页面重置码
        String outerResetCode = administratorResetPwdInDTO.getResetCode();
        if (outerResetCode == null) {
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_OUTER_RESETCODE_NONE_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_PWDRESET_OUTER_RESETCODE_NONE_ERRMSG);
        }

        //比较重置码是否一致
        if (!outerResetCode.equalsIgnoreCase(innerResetCode)){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_RESETCODE_INVALID_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_PWDRESET_RESETCODE_INVALID_ERRMSG);
        }

        //获取当前管理员信息
        Administrator administrator = administratorMapper.selectByEmail(email);
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRMSG);
        }

        //获取新的密码
        String newPwd = administratorResetPwdInDTO.getNewPwd();
        if (newPwd == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_NEWPWD_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_NEWPWD_NOT_EXIST_ERRMSG);
        }

        //重新加密 新密码
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, newPwd.toCharArray());
        administrator.setEncryptedPassword(bcryptHashString);

        //修改当前管理员
        administratorMapper.updateByPrimaryKeySelective(administrator);

        //删除对象
        emailPwdResetCodeMap.remove(email);
    }
}
