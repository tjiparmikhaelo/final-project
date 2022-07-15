package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.View.ViewDetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ViewDetailProductRepo extends JpaRepository<ViewDetailProduct, Long>
{
    List<ViewDetailProduct> findAll();

    List<ViewDetailProduct> findByProductId(Long productId);

    List<ViewDetailProduct> findByCategoryId(Long categoryId);

    List<ViewDetailProduct> findByUsername(String username);
}
