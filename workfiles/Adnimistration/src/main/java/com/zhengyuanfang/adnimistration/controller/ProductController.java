package com.zhengyuanfang.adnimistration.controller;

import com.zhengyuanfang.adnimistration.dto.in.ProductCreateInDTO;
import com.zhengyuanfang.adnimistration.dto.in.ProductSearchInDTO;
import com.zhengyuanfang.adnimistration.dto.in.ProductUpdateInDTO;
import com.zhengyuanfang.adnimistration.dto.out.PageOutDTO;
import com.zhengyuanfang.adnimistration.dto.out.ProductListOutDTO;
import com.zhengyuanfang.adnimistration.dto.out.ProductShowOutDTO;
import org.springframework.web.bind.annotation.*;

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

    /*
     * 添加商品
     */
    @PostMapping("/create")
    public Integer create(@RequestBody ProductCreateInDTO productCreateInDTO){
        return null;
    }

    /*
     * 修改商品
     */
    @PostMapping("/update")
    public void update(@RequestBody ProductUpdateInDTO productUpdateInDTO){

    }
}
