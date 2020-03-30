package com.bos.product.repository;

import com.bos.product.model.ProductCategory;
import com.bos.product.model.dao.ProductCategoryDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    @Query(value = "SELECT DISTINCT p.id_prd_category AS id_prd_category, c.category_name AS category_name\n" +
            "FROM product AS p\n" +
            "JOIN prd_category AS c\n" +
            "ON p.id_prd_category = c.id_prd_category\n" +
            "WHERE id_seller = :id_seller\n" +
            "ORDER BY c.category_name", nativeQuery = true)
    List<ProductCategoryDao> getBySellerId(@Param("id_seller") int id_seller);
}
