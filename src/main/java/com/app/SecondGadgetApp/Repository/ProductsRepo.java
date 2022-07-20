package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductsRepo extends JpaRepository<Products, Long>
{
    @Query(value = "select * from products order by created_at desc", nativeQuery = true)
    List<Products> ProductDesc();
    List<Products> findByProductName(String productName);
    Products findByProductId(Long productId);
    List<Products> findByUsersUsername(String username);
    @Query("select p from Products p where p.categories.categoryId = ?1 order by created_at desc")
    List<Products> findByCategoriesCategoryId(Long categoryId);
}