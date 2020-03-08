package com.zhengyuanfang.controller;

import com.zhengyuanfang.dto.in.ReturnSearchInDTO;
import com.zhengyuanfang.dto.in.ReturnUpdateActionInDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ReturnListOutDTO;
import com.zhengyuanfang.dto.out.ReturnShowOutDTO;
import com.zhengyuanfang.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
@CrossOrigin
public class ReturnController {

    @Autowired
    private ReturnService returnService;

    /*
    *模糊分页查询退货列表
    */
    @GetMapping("/list")
    public PageOutDTO<ReturnListOutDTO> search(ReturnSearchInDTO returnSearchInDTO,
                                               @RequestParam(required = false, defaultValue = "1") Integer pageNum){
        PageOutDTO<ReturnListOutDTO> pageOutDTO = returnService.findAll(pageNum);
        return pageOutDTO;
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
