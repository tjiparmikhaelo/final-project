package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductsRepo extends JpaRepository<Products, Long>
{
    Products findByProductId(Long productId);
    List<Products> findByUsersUsername(String username);
    List<Products> findByCategoriesCategoryId(Long categoryId);
}
