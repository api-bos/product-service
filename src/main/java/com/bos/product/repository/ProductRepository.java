package com.bos.product.repository;

import com.bos.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product WHERE id_seller = :id_seller AND is_deleted=0", nativeQuery = true)
    List<Product> getAllProductBySellerId(@Param("id_seller") int id_seller);

    @Query(value = "SELECT * FROM product WHERE id_seller = :id_seller AND is_deleted=0 ORDER BY product_name", nativeQuery = true)
    List<Product> getProductNameBySellerId(@Param("id_seller") int id_seller);

    @Query(value = "SELECT * FROM product WHERE id_seller = :id_seller AND is_deleted=0 ORDER BY created_time DESC", nativeQuery = true)
    List<Product> getProductDateBySellerId(@Param("id_seller") int id_seller);

    @Query(value = "SELECT * FROM product WHERE id_seller = :id_seller AND is_deleted=0 ORDER BY price", nativeQuery = true)
    List<Product> getProductPriceBySellerId(@Param("id_seller") int id_seller);

    @Query(value = "SELECT prd.*, SUM(td.quantity) AS qty\n" +
            "FROM transaction_detail td\n" +
            "LEFT JOIN product prd ON td.id_product = prd.id_product\n" +
            "WHERE td.id_transaction\n" +
            "IN (SELECT id_transaction\n" +
            "\tFROM transaction\n" +
            "\tWHERE id_seller= :id_seller and status=3) and prd.is_deleted=0\n" +
            "GROUP BY prd.id_product, prd.product_name\n" +
            "ORDER BY SUM(td.quantity) DESC", nativeQuery = true)
    List<Product> getProductBestSellingBySellerId(@Param("id_seller") int p_sellerId);

    @Query(value = "SELECT image_path FROM product WHERE id_product = :id_product", nativeQuery = true)
    String getImagePathByProductId(@Param("id_product") int id_product);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET is_deleted=1 WHERE id_product = :id_product", nativeQuery = true)
    void deleteProduct(@Param("id_product") int id_product);
}
