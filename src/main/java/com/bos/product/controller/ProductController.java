package com.bos.product.controller;

import bca.bit.proj.library.base.ResultEntity;
import com.bos.product.model.ProductDetail;
import com.bos.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bos", produces = "application/json")
@CrossOrigin(origins = {"*"})
public class ProductController {
    @Autowired
    ProductService g_productService;

    @GetMapping(value = "/product/{id_seller}")
    public ResultEntity getProduct(@PathVariable("id_seller") int id_seller){
        return g_productService.getProduct(id_seller);
    }

    @PostMapping(value = "/product", consumes = "application/json")
    public ResultEntity saveProduct(@RequestBody ProductDetail p_productDetail){
        return g_productService.saveProduct(p_productDetail);
    }

    @PutMapping(value = "/product", consumes = "application/json")
    public ResultEntity updateProduct(@RequestBody ProductDetail p_productDetail){
        return g_productService.updateProduct(p_productDetail);
    }

    @DeleteMapping(value = "/product/{id_product}")
    public ResultEntity deleteProduct(@PathVariable("id_product") int p_productId){
        return g_productService.deleteProduct(p_productId);
    }
}
