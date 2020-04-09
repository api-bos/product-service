package com.bos.product.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {
    private int id_product;
    private int id_seller;
    private int id_prd_category;
    private String product_name;
    private int price;
    private int stock;
    private String weight;
    private String image_path;
    private String base64StringImage;
}
