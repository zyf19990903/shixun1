package com.zhengyuanfang.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.zhengyuanfang.dto.in.ProductSearchInDTO;
import com.zhengyuanfang.dto.out.PageOutDTO;
import com.zhengyuanfang.dto.out.ProductListOutDTO;
import com.zhengyuanfang.dto.out.ProductShowOutDTO;
import com.zhengyuanfang.po.HotProductDTO;
import com.zhengyuanfang.po.ProductOperation;
import com.zhengyuanfang.service.ProductOperationService;
import com.zhengyuanfang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductOperationService productOperationService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /*
     * 模糊分页查询商品列表
     */
    @GetMapping("/list")
    public PageOutDTO<ProductListOutDTO> findAll(@RequestParam(required = false,defaultValue = "1")Integer pageNum, ProductSearchInDTO productSearchInDTO){
        Page<ProductListOutDTO> page = productService.findAll(pageNum);
        PageOutDTO<ProductListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setList(page);
        return pageOutDTO;
    }

    /*
     * 根据id查询商品
     */
    @GetMapping("/show")
    public ProductShowOutDTO show(@RequestParam Integer productId){
        ProductShowOutDTO productShowOutDTO = productService.show(productId);

        //productOperationService.count(productId);

        HotProductDTO hotProductDTO = new HotProductDTO();
        hotProductDTO.setProductId(productId);
        hotProductDTO.setProductCode(productShowOutDTO.getProductCode());

        kafkaTemplate.send("test", JSON.toJSONString(hotProductDTO));
        return productShowOutDTO;
    }

    @GetMapping("/hot")
    public List<ProductOperation> hot(){

        String hotProductsJson = redisTemplate.opsForValue().get("HotProducts");
        if (hotProductsJson != null){
            List<ProductOperation> productOperations = JSON.parseArray(hotProductsJson, ProductOperation.class);
            return productOperations;
        }else {
            List<ProductOperation> hotProducts = productOperationService.selectHotProduct();
            redisTemplate.opsForValue().set("HotProducts", JSON.toJSONString(hotProducts));
            return hotProducts;
        }
    }

}
