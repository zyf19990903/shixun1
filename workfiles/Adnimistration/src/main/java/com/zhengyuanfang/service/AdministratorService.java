package com.zhengyuanfang.service;

import com.zhengyuanfang.dto.in.*;
import com.zhengyuanfang.dto.out.*;
import com.zhengyuanfang.exception.ClientException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public interface AdministratorService {

    AdministratorLoginOutDTO getByUsername(AdministratorLoginInDTO administratorLoginInDTO) throws ClientException;

    AdministratorGetProfileOutDTO getById(Integer administratorId);

    void updateProfile(AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO,Integer administratorId);

    PageOutDTO<AdministratorListOutDTO> findAll(Integer pageNum);

    Integer create(AdministratorCreateInDTO administratorCreateInDTO);

    void delete(Integer adminstratorId);

    void batchDelete(List<Integer> administratorIds);

    AdministratorShowOutDTO show(Integer administratorId);

    void update(AdministratorUpdateInDTO administratorUpdateInDTO);

    String getByEmail(String email) throws ClientException;

    void restPwd(AdministratorResetPwdInDTO administratorResetPwdInDTO, RedisTemplate<String, String> redisTemplate) throws ClientException;
}
