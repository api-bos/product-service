package com.bos.product.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int id_product;
    private SellerResponse seller;
    private ProductCategoryReponse prdCategory;
    private String product_name;
    private int price;
    private int stock;
    private String weight;
    private String image_path;
}
