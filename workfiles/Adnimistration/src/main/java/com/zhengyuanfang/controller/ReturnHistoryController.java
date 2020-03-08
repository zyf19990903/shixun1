package com.zhengyuanfang.controller;

import com.zhengyuanfang.dto.in.ReturnHistoryCreateInDTO;
import com.zhengyuanfang.dto.out.ReturnHistoryListOutDTO;
import com.zhengyuanfang.service.ReturnHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/returnhistory")
@CrossOrigin
public class ReturnHistoryController {

    @Autowired
    private ReturnHistoryService returnHistoryService;

    /*
    *查看当前退货订单的退货历史
    */
    @GetMapping("/list")
    public List<ReturnHistoryListOutDTO> getListByReturnId(@RequestParam Integer returnId){
        List<ReturnHistoryListOutDTO> returnHistoryListOutDTOS = returnHistoryService.findAll(returnId);
        return returnHistoryListOutDTOS;
    }

    /*
     *创建退货历史
     */
    @PostMapping("/create")
    public Long create(@RequestBody ReturnHistoryCreateInDTO returnHistoryCreateInDTO){
        Long returnHistoryId = returnHistoryService.create(returnHistoryCreateInDTO);
        return returnHistoryId;
    }

}
