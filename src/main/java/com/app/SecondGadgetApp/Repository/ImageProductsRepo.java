package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.ImageProducts;
import com.app.SecondGadgetApp.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageProductsRepo extends JpaRepository<ImageProducts, Long>
{
    List<ImageProducts> findAll();
//    ImageProducts findByImageProductId(Long id);
//    ImageProducts findByProductId(Long id);
    Iterable<ImageProducts> findByProducts(Products products);
}
