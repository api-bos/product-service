package com.bos.product.controller;

import bca.bit.proj.library.base.ResultEntity;
import com.bos.product.model.request.ProductRequest;
import com.bos.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bos", produces = "application/json")
public class ProductController {
    @Autowired
    ProductService g_productService;

    @GetMapping(value = "/product-by-name/{id_seller}")
    public ResultEntity getProductByName(@PathVariable("id_seller") int id_seller){
        return g_productService.getProductByName(id_seller);
    }

    @GetMapping(value = "/product-by-date/{id_seller}")
    public ResultEntity getProductByDate(@PathVariable("id_seller") int id_seller){
        return g_productService.getProductByDate(id_seller);
    }

    @GetMapping(value = "/product-by-price/{id_seller}")
    public ResultEntity getProductByPrice(@PathVariable("id_seller") int id_seller){
        return g_productService.getProductByPrice(id_seller);
    }

    @GetMapping(value = "/product-by-best-selling/{id_seller}")
    public ResultEntity getProductByBestSelling(@PathVariable("id_seller") int id_seller){
        return g_productService.getProductByBestSelling(id_seller);
    }

    @PostMapping(value = "/product", consumes = "application/json")
    public ResultEntity saveProduct(@RequestBody ProductRequest p_productRequest){
        return g_productService.saveProduct(p_productRequest);
    }

    @PutMapping(value = "/product", consumes = "application/json")
    public ResultEntity updateProduct(@RequestBody ProductRequest p_productRequest){
        return g_productService.updateProduct(p_productRequest);
    }

    @DeleteMapping(value = "/product/{id_product}")
    public ResultEntity deleteProduct(@PathVariable("id_product") int p_productId){
        return g_productService.deleteProduct(p_productId);
    }

    @GetMapping(value = "/product-category/{id_seller}")
    public ResultEntity getProductCategory(@PathVariable("id_seller") int p_sellerId){
        return g_productService.getProductCategory(p_sellerId);
    }

    @GetMapping(value = "/product-detail/{id_product}")
    public ResultEntity getProductDetail(@PathVariable("id_product") int p_productId){
        return g_productService.getProductDetail(p_productId);
    }

    @GetMapping(value = "/product-category")
    public ResultEntity getAllProductCategory(){
        return g_productService.getAllProductCategory();
    }
}