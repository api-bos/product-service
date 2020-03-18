package com.bos.product.repository;

import com.bos.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product WHERE id_seller = :id_seller AND is_deleted=0 ORDER BY product_name", nativeQuery = true)
    List<Product> getAllProductBySellerID(@Param("id_seller") int id_seller);

    @Query(value = "SELECT image_path FROM product WHERE id_product = :id_product", nativeQuery = true)
    String getImagePathByProductId(@Param("id_product") int id_product);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET is_deleted=1 WHERE id_product = :id_product", nativeQuery = true)
    void deleteProduct(@Param("id_product") int id_product);
}
