package com.zhengyuanfang.adnimistration.controller;

import com.zhengyuanfang.adnimistration.dto.in.AdministratorLoginInDTO;
import com.zhengyuanfang.adnimistration.dto.in.AdministratorUpdateProfileInDTO;
import com.zhengyuanfang.adnimistration.dto.out.AdministratorGetProfileOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @GetMapping("/login")
    public String login(AdministratorLoginInDTO administratorLoginInDTO){
        return null;
    }

    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(Integer adminstratorId){
        return null;
    }

    @PostMapping("/updateProdfile")
    public void updateProdfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO){

    }
    @GetMapping("/list")
    public List<AdministratorGetProfileOutDTO> findAll(Integer pageNum,Integer pageSize){
        return null;
    }
    @GetMapping("/show")
    public AdministratorGetProfileOutDTO show(Integer administratorId){
        return null;
    }
    @PostMapping("/create")
    public Integer create(AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO){
        return 0;
    }
    @PostMapping("/update")
    public boolean update(AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO){
        return false;
    }
}
