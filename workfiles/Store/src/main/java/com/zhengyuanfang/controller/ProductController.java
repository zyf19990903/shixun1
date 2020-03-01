package com.zhengyuanfang.controller;

import com.zhengyuanfang.dto.in.ProductSearchInDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ProductListOutDTO;
import com.zhengyuanfang.dto.out.ProductShowOutDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    /*
     * 模糊分页查询商品列表
     */
    @GetMapping("/list")
    public PageOutDTO<ProductListOutDTO> findAll(@RequestParam(required = false,defaultValue = "1")Integer pageNum, ProductSearchInDTO productSearchInDTO){
        return null;
    }

    /*
     * 根据id查询商品
     */
    @GetMapping("/show")
    public ProductShowOutDTO show(@RequestParam Integer productId){
        return null;
    }

}
