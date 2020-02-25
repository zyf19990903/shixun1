package com.zhengyuanfang.adnimistration.controller;

import com.zhengyuanfang.adnimistration.dto.in.ReturnSearchInDTO;
import com.zhengyuanfang.adnimistration.dto.in.ReturnUpdateActionInDTO;
import com.zhengyuanfang.adnimistration.dto.out.PageOutDTO;
import com.zhengyuanfang.adnimistration.dto.out.ReturnListOutDTO;
import com.zhengyuanfang.adnimistration.dto.out.ReturnShowOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
public class ReturnController {

    /*
    *模糊分页查询退货列表
    */
    @GetMapping("/findAll")
    public PageOutDTO<ReturnListOutDTO> search(ReturnSearchInDTO returnSearchInDTO,
                                               @RequestParam Integer pageNum){
        return null;
    }

    /*
     *查看退货详情
     */
    @GetMapping("/show")
    public ReturnShowOutDTO getById(@RequestParam Integer returnId){
        return null;
    }

    /*
     *修改退货订单
     */
    @PostMapping("/update")
    public void updateAction(@RequestBody ReturnUpdateActionInDTO returnUpdateActionInDTO){

    }

}
