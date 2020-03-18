package com.bos.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_product;
    private int id_seller;
    private int id_prd_category;
    private String product_name;
    private int price;
    private int stock;
    private String image_path;
    private int is_deleted;
    private String weight;
}
