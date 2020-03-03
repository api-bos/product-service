package com.bos.product.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetail {
    private int id_product;
    private int id_seller;
    private int id_prd_category;
    private String product_name;
    private int price;
    private int stock;
    private String image_path;
    private String base64StringImage;
}
