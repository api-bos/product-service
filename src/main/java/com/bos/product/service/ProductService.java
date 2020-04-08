package com.bos.product.service;

import bca.bit.proj.library.base.ResultEntity;
import bca.bit.proj.library.enums.ErrorCode;
import com.bos.product.model.Product;
import com.bos.product.model.request.ProductRequest;
import com.bos.product.model.response.ProductCategoryReponse;
import com.bos.product.model.response.ProductResponse;
import com.bos.product.model.response.SellerResponse;
import com.bos.product.repository.ProductCategoryRepository;
import com.bos.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository g_productRepository;
    @Autowired
    ProductCategoryRepository g_productCategoryRepository;

    public String saveImage(String p_imageEncoded) {
        String tmp_fileName = new SimpleDateFormat("yyyyMMdd_HHmmssSSS'.jpg'").format(new Date());
        String tmp_fullPath = "\\bos\\NASBOS\\" + tmp_fileName;
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

    private Product saveData(ProductRequest p_productRequest, String p_imagePath){
        Product tmp_product = new Product();
        tmp_product.setId_product(p_productRequest.getId_product());
        tmp_product.setId_seller(p_productRequest.getId_seller());
        tmp_product.setId_prd_category(p_productRequest.getId_prd_category());
        tmp_product.setProduct_name(p_productRequest.getProduct_name());
        tmp_product.setPrice(p_productRequest.getPrice());
        tmp_product.setStock(p_productRequest.getStock());
        tmp_product.setImage_path(p_imagePath);
        tmp_product.setWeight(p_productRequest.getWeight());
        tmp_product.setIs_deleted(0);

        return tmp_product;
    }

    public ResultEntity getProductByName(int p_sellerId){
        List<Product> tmp_productList = g_productRepository.getProductNameBySellerId(p_sellerId);
        ArrayList<ProductResponse> l_productResponseList = new ArrayList<>();

        for (int i=0; i<tmp_productList.size(); i++){
            ProductCategoryReponse tmp_productCategoryResponse = new ProductCategoryReponse();
            tmp_productCategoryResponse.setId_prd_category(tmp_productList.get(i).getId_prd_category());

            SellerResponse tmp_sellerResponse = new SellerResponse();
            tmp_sellerResponse.setId_seller(tmp_productList.get(i).getId_seller());

            ProductResponse tmp_productResponse = new ProductResponse();
            tmp_productResponse.setId_product(tmp_productList.get(i).getId_product());
            tmp_productResponse.setSeller(tmp_sellerResponse);
            tmp_productResponse.setPrdCategory(tmp_productCategoryResponse);
            tmp_productResponse.setProduct_name(tmp_productList.get(i).getProduct_name());
            tmp_productResponse.setPrice(tmp_productList.get(i).getPrice());
            tmp_productResponse.setStock(tmp_productList.get(i).getStock());
            tmp_productResponse.setWeight(tmp_productList.get(i).getWeight());
            tmp_productResponse.setImage_path(tmp_productList.get(i).getImage_path());

            l_productResponseList.add(tmp_productResponse);
        }

        return new ResultEntity(l_productResponseList, ErrorCode.BIT_000);
    }

    public ResultEntity getProductByDate(int p_sellerId){
        List<Product> tmp_productList = g_productRepository.getProductDateBySellerId(p_sellerId);
        ArrayList<ProductResponse> l_productResponseList = new ArrayList<>();

        for (int i=0; i<tmp_productList.size(); i++){
            ProductCategoryReponse tmp_productCategoryResponse = new ProductCategoryReponse();
            tmp_productCategoryResponse.setId_prd_category(tmp_productList.get(i).getId_prd_category());

            SellerResponse tmp_sellerResponse = new SellerResponse();
            tmp_sellerResponse.setId_seller(tmp_productList.get(i).getId_seller());

            ProductResponse tmp_productResponse = new ProductResponse();
            tmp_productResponse.setId_product(tmp_productList.get(i).getId_product());
            tmp_productResponse.setSeller(tmp_sellerResponse);
            tmp_productResponse.setPrdCategory(tmp_productCategoryResponse);
            tmp_productResponse.setProduct_name(tmp_productList.get(i).getProduct_name());
            tmp_productResponse.setPrice(tmp_productList.get(i).getPrice());
            tmp_productResponse.setStock(tmp_productList.get(i).getStock());
            tmp_productResponse.setWeight(tmp_productList.get(i).getWeight());
            tmp_productResponse.setImage_path(tmp_productList.get(i).getImage_path());

            l_productResponseList.add(tmp_productResponse);
        }

        return new ResultEntity(l_productResponseList, ErrorCode.BIT_000);
    }

    public ResultEntity getProductByPrice(int p_sellerId){
        List<Product> tmp_productList = g_productRepository.getProductPriceBySellerId(p_sellerId);
        ArrayList<ProductResponse> l_productResponseList = new ArrayList<>();

        for (int i=0; i<tmp_productList.size(); i++){
            ProductCategoryReponse tmp_productCategoryResponse = new ProductCategoryReponse();
            tmp_productCategoryResponse.setId_prd_category(tmp_productList.get(i).getId_prd_category());

            SellerResponse tmp_sellerResponse = new SellerResponse();
            tmp_sellerResponse.setId_seller(tmp_productList.get(i).getId_seller());

            ProductResponse tmp_productResponse = new ProductResponse();
            tmp_productResponse.setId_product(tmp_productList.get(i).getId_product());
            tmp_productResponse.setSeller(tmp_sellerResponse);
            tmp_productResponse.setPrdCategory(tmp_productCategoryResponse);
            tmp_productResponse.setProduct_name(tmp_productList.get(i).getProduct_name());
            tmp_productResponse.setPrice(tmp_productList.get(i).getPrice());
            tmp_productResponse.setStock(tmp_productList.get(i).getStock());
            tmp_productResponse.setWeight(tmp_productList.get(i).getWeight());
            tmp_productResponse.setImage_path(tmp_productList.get(i).getImage_path());

            l_productResponseList.add(tmp_productResponse);
        }

        return new ResultEntity(l_productResponseList, ErrorCode.BIT_000);
    }

    public ResultEntity getProductByBestSelling(int p_sellerId){
        //Get product yg sudah pernah dibeli
        List<Product> tmp_productList = g_productRepository.getProductBestSellingBySellerId(p_sellerId);
        ArrayList<ProductResponse> l_productResponseList = new ArrayList<>();

        for (int i=0; i<tmp_productList.size(); i++){
            ProductCategoryReponse tmp_productCategoryResponse = new ProductCategoryReponse();
            tmp_productCategoryResponse.setId_prd_category(tmp_productList.get(i).getId_prd_category());

            SellerResponse tmp_sellerResponse = new SellerResponse();
            tmp_sellerResponse.setId_seller(tmp_productList.get(i).getId_seller());

            ProductResponse tmp_productResponse = new ProductResponse();
            tmp_productResponse.setId_product(tmp_productList.get(i).getId_product());
            tmp_productResponse.setSeller(tmp_sellerResponse);
            tmp_productResponse.setPrdCategory(tmp_productCategoryResponse);
            tmp_productResponse.setProduct_name(tmp_productList.get(i).getProduct_name());
            tmp_productResponse.setPrice(tmp_productList.get(i).getPrice());
            tmp_productResponse.setStock(tmp_productList.get(i).getStock());
            tmp_productResponse.setWeight(tmp_productList.get(i).getWeight());
            tmp_productResponse.setImage_path(tmp_productList.get(i).getImage_path());

            l_productResponseList.add(tmp_productResponse);
        }

        //Get all product
        List<Product> tmp_allProductList = g_productRepository.getAllProductBySellerId(p_sellerId);

        for (int i=0; i<tmp_allProductList.size(); i++){

            boolean checkAda = false;

            for (int j=0; j<l_productResponseList.size(); j++){
                if(tmp_allProductList.get(i).getId_product() == l_productResponseList.get(j).getId_product()){
                    checkAda = true;
                    break;
                }
            }

            if (!checkAda){

                ProductCategoryReponse tmp_productCategoryResponse = new ProductCategoryReponse();
                tmp_productCategoryResponse.setId_prd_category(tmp_allProductList.get(i).getId_prd_category());

                SellerResponse tmp_sellerResponse = new SellerResponse();
                tmp_sellerResponse.setId_seller(tmp_allProductList.get(i).getId_seller());

                ProductResponse tmp_productResponse = new ProductResponse();
                tmp_productResponse.setId_product(tmp_allProductList.get(i).getId_product());
                tmp_productResponse.setSeller(tmp_sellerResponse);
                tmp_productResponse.setPrdCategory(tmp_productCategoryResponse);
                tmp_productResponse.setProduct_name(tmp_allProductList.get(i).getProduct_name());
                tmp_productResponse.setPrice(tmp_allProductList.get(i).getPrice());
                tmp_productResponse.setStock(tmp_allProductList.get(i).getStock());
                tmp_productResponse.setWeight(tmp_allProductList.get(i).getWeight());
                tmp_productResponse.setImage_path(tmp_allProductList.get(i).getImage_path());

                l_productResponseList.add(tmp_productResponse);

            }

        }

        return new ResultEntity(l_productResponseList, ErrorCode.BIT_000);
    }

    public ResultEntity saveProduct(ProductRequest p_productRequest){
        String FULL_PATH = "";
        ResultEntity l_output;

        FULL_PATH = saveImage(p_productRequest.getBase64StringImage());

        if (!FULL_PATH.equals("failed")){
            g_productRepository.save(saveData(p_productRequest, FULL_PATH));

            l_output = new ResultEntity("Y", ErrorCode.BIT_000);
        }else {
            g_productRepository.save(saveData(p_productRequest, ""));

            l_output = new ResultEntity("Y", ErrorCode.BIT_000);
        }

        return  l_output;
    }

    public ResultEntity updateProduct(ProductRequest p_productRequest){
        String tmp_unusedImagePath = g_productRepository.getImagePathByProductId(p_productRequest.getId_product());
        System.out.println(tmp_unusedImagePath);

        File tmp_unusedImage = new File(tmp_unusedImagePath);
        if (tmp_unusedImage.exists()){
            tmp_unusedImage.delete();
        }

        return saveProduct(p_productRequest);
    }

    public ResultEntity deleteProduct(int p_productId){
        ResultEntity l_output;

        try{
            g_productRepository.deleteProduct(p_productId);
            l_output = new ResultEntity("Y", ErrorCode.BIT_000);

        }catch (Exception e){
            l_output = new ResultEntity(e.toString(), ErrorCode.BIT_999);
        }

        return l_output;
    }

    public ResultEntity getProductCategory(int p_sellerId){
        ResultEntity l_output;

        try {
            l_output = new ResultEntity(g_productCategoryRepository.getBySellerId(p_sellerId), ErrorCode.BIT_000);
        }catch (Exception e){
            l_output = new ResultEntity(e.toString(), ErrorCode.BIT_999);
        }

        return l_output;
    }

    public ResultEntity getProductDetail(int p_productId){
        ResultEntity l_output;

        try {
            l_output = new ResultEntity(g_productRepository.findById(p_productId), ErrorCode.BIT_000);
        }catch (Exception e){
            l_output = new ResultEntity(e.toString(), ErrorCode.BIT_999);
        }

        return l_output;
    }

    public ResultEntity getAllProductCategory(){
        ResultEntity l_output;

        try {
            l_output = new ResultEntity(g_productCategoryRepository.findAll(), ErrorCode.BIT_000);
        }catch (Exception e){
            l_output = new ResultEntity(e.toString(), ErrorCode.BIT_999);
        }

        return l_output;
    }
}
