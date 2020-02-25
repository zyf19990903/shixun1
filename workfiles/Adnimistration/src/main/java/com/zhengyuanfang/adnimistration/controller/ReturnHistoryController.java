package com.zhengyuanfang.adnimistration.controller;

import com.zhengyuanfang.adnimistration.dto.in.ReturnHistoryCreateInDTO;
import com.zhengyuanfang.adnimistration.dto.out.ReturnHistoryListOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returnhistory")
public class ReturnHistoryController {

    /*
    *查看当前退货订单的退货历史
    */
    @GetMapping("/list")
    public List<ReturnHistoryListOutDTO> getListByReturnId(@RequestParam Integer returnId){
        return null;
    }

    /*
     *创建退货历史
     */
    @PostMapping("/create")
    public Integer create(@RequestBody ReturnHistoryCreateInDTO returnHistoryCreateInDTO){
        return null;
    }

}
