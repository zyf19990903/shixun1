package com.zhengyuanfang.controller;

import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.in.ProductCreateInDTO;
import com.zhengyuanfang.dto.in.ProductSearchInDTO;
import com.zhengyuanfang.dto.in.ProductUpdateInDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ProductListOutDTO;
import com.zhengyuanfang.dto.out.ProductShowOutDTO;
import com.zhengyuanfang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;
    /*
     * 模糊分页查询商品列表
     */
    @GetMapping("/list")
    public PageOutDTO<ProductListOutDTO> findAll(@RequestParam(required = false,defaultValue = "1")Integer pageNum, ProductSearchInDTO productSearchInDTO){
        Page<ProductListOutDTO> page = productService.findAll(pageNum,productSearchInDTO);
        PageOutDTO<ProductListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setList(page);
        return pageOutDTO;
    }

    /*
     * 根据id查询商品
     */
    @GetMapping("/show")
    public ProductShowOutDTO show(@RequestParam Integer productId){
        ProductShowOutDTO productShowOutDTO = productService.show(productId);
        return productShowOutDTO;
    }

    /*
     * 添加商品
     */
    @PostMapping("/create")
    public Integer create(@RequestBody ProductCreateInDTO productCreateInDTO){
        Integer productId = productService.create(productCreateInDTO);
        return productId;
    }

    /*
     * 修改商品
     */
    @PostMapping("/update")
    public void update(@RequestBody ProductUpdateInDTO productUpdateInDTO){
        productService.update(productUpdateInDTO);
    }

    /*
     * 删除商品
     */
    @PostMapping("/delete")
    public void delete(@RequestBody Integer productId){
        productService.delete(productId);
    }

    /*
     *删除所有商品
     */
    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> productIds){
        productService.batchDelete(productIds);
    }
}
