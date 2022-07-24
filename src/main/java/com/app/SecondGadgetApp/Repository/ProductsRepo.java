package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductsRepo extends JpaRepository<Products, Long>
{
    @Query(value = "select * from products p where not p.product_status = 'archive' and not p.product_status = 'sold' order by created_at desc", nativeQuery = true)
    List<Products> ProductDesc();
    @Query(value = "select * from products p where p.product_name like concat ('%',:productName,'%') and not p.product_status = 'archive' and not p.product_status = 'sold' order by p.created_at desc", nativeQuery = true)
    List<Products> findByProductName(String productName);
    Products findByProductId(Long productId);
    @Modifying
    @Query(value = "select * from products p join users u on p.user_id = u.user_id where username = ?1 order by p.updated_at DESC", nativeQuery = true)
    List<Products> ProductByUsername(String username);
    @Modifying
    @Query(value = "select * from products p where category_id = ?1 and not p.product_status = 'archive' and not p.product_status = 'sold' order by created_at desc", nativeQuery = true)
    List<Products> ProductByCategory(Long categoryId);
    @Modifying
    @Query(value = "select * from products p join users u on p.user_id = u.user_id where p.product_name like concat ('%',:productName,'%') and p.category_id = :categoryId and u.city_id = :idCity and not p.product_status = 'archive' and not p.product_status = 'sold' order by p.created_at desc", nativeQuery = true)
    List<Products> filter(String productName, Long categoryId, Long idCity);

    @Modifying
    @Query(value = "delete cascade from products p where p.product_id = :productId ", nativeQuery = true)
    void deleteByProductId(@Param("productId") Long productId);
}