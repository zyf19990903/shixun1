package com.zhengyuanfang.controller;

import com.zhengyuanfang.dto.in.ReturnCreateActionInDTO;
import com.zhengyuanfang.dto.in.ReturnSearchInDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ReturnListOutDTO;
import com.zhengyuanfang.dto.out.ReturnShowOutDTO;
import com.zhengyuanfang.po.Return;
import com.zhengyuanfang.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
                                               @RequestParam Integer pageNum,
                                               @RequestAttribute Integer customerId){
        PageOutDTO<ReturnListOutDTO> pageOutDTO = returnService.findAll(pageNum,customerId);
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
     *退货申请
     */
    @PostMapping("/create")
    public Integer updateAction(@RequestBody ReturnCreateActionInDTO returnCreateActionInDTO,
                                @RequestAttribute Integer customerId){
        Integer returnId = returnService.create(returnCreateActionInDTO,customerId);
        return returnId;
    }
}
