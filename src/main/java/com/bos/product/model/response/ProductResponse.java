package com.bos.product.model.response;

import com.bos.product.model.response.ProductCategoryReponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int id_product;
    private int id_seller;
    private ProductCategoryReponse prdCategory;
    private String product_name;
    private int price;
    private int stock;
    private String image_path;
}
