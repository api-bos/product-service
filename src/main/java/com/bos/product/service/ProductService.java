package com.bos.product.service;

import bca.bit.proj.library.base.ResultEntity;
import bca.bit.proj.library.enums.ErrorCode;
import com.bos.product.model.Product;
import com.bos.product.model.ProductDetail;
import com.bos.product.repository.ProductCategoryRepository;
import com.bos.product.repository.ProductRepository;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
public class ProductService {
    @Autowired
    ProductRepository g_productRepository;
    @Autowired
    ProductCategoryRepository g_productCategoryRepository;

    public String saveImage(String p_imageEncoded) {
        String tmp_uploadLocation = "C:\\Users\\U067726\\Pictures\\BOS\\Product";
        String tmp_fileName = new SimpleDateFormat("yyyyMMdd_HHmmssSSS'.jpg'").format(new Date());
        String tmp_fullPath = tmp_uploadLocation + File.separator + tmp_fileName;
        try (FileOutputStream tmp_imageOutFile = new FileOutputStream(tmp_fullPath)) {
            // Converting a Base64 String into Image byte array
            byte[] tmp_imageByteArray = Base64.getDecoder().decode(p_imageEncoded);
            tmp_imageOutFile.write(tmp_imageByteArray);

            return tmp_fullPath;
        } catch (IOException e) {
            System.out.println("Exception while reading the Image: " + e);

            return "failed";
        }
    }

    private Product saveData(ProductDetail p_productDetail, String p_imagePath){
        Product tmp_product = new Product();
        tmp_product.setId_product(p_productDetail.getId_product());
        tmp_product.setId_seller(p_productDetail.getId_seller());
        tmp_product.setId_prd_category(p_productDetail.getId_prd_category());
        tmp_product.setProduct_name(p_productDetail.getProduct_name());
        tmp_product.setPrice(p_productDetail.getPrice());
        tmp_product.setStock(p_productDetail.getStock());
        tmp_product.setImage_path(p_imagePath);

        return tmp_product;
    }

    public ResultEntity getProduct(int p_sellerId){
        return new ResultEntity(g_productRepository.getAllProductBySellerID(p_sellerId), ErrorCode.BIT_000);
    }

    public ResultEntity saveProduct(ProductDetail p_productDetail){
        String FULL_PATH = "";
        ResultEntity l_output;

        FULL_PATH = saveImage(p_productDetail.getBase64StringImage());

        if (!FULL_PATH.equals("failed")){
            g_productRepository.save(saveData(p_productDetail, FULL_PATH));

            l_output = new ResultEntity("Y", ErrorCode.BIT_000);
        }else {
            g_productRepository.save(saveData(p_productDetail, ""));

            l_output = new ResultEntity("Y", ErrorCode.BIT_000);
        }

        return  l_output;
    }

    public ResultEntity updateProduct(ProductDetail p_productDetail){
        String tmp_unusedImagePath = g_productRepository.getImagePathByProductId(p_productDetail.getId_product());
        System.out.println(tmp_unusedImagePath);

        File tmp_unusedImage = new File(tmp_unusedImagePath);
        if (tmp_unusedImage.exists()){
            tmp_unusedImage.delete();
        }

        return saveProduct(p_productDetail);
    }

    public ResultEntity deleteProduct(int p_productId){
        ResultEntity l_output;

        try{
            g_productRepository.deleteById(p_productId);
            l_output = new ResultEntity("Y", ErrorCode.BIT_000);

        }catch (Exception e){
            l_output = new ResultEntity(e.toString(), ErrorCode.BIT_999);
        }

        return l_output;
    }

    public ResultEntity getProductCategory(){
        ResultEntity l_output;

        try {
            l_output = new ResultEntity(g_productCategoryRepository.findAll(), ErrorCode.BIT_000);
        }catch (Exception e){
            l_output = new ResultEntity(e.toString(), ErrorCode.BIT_999);
        }

        return l_output;
    }
}
