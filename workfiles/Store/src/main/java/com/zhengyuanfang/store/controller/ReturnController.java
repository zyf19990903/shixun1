package com.zhengyuanfang.store.controller;

import com.zhengyuanfang.store.dto.in.ReturnCreateActionInDTO;
import com.zhengyuanfang.store.dto.in.ReturnSearchInDTO;
import com.zhengyuanfang.store.dto.out.PageOutDTO;
import com.zhengyuanfang.store.dto.out.ReturnListOutDTO;
import com.zhengyuanfang.store.dto.out.ReturnShowOutDTO;
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
     *退货申请
     */
    @PostMapping("/create")
    public Integer updateAction(@RequestBody ReturnCreateActionInDTO returnCreateActionInDTO){
        return 0;
    }
}
