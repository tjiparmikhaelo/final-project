package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.View.VwDetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ViewDetailProductRepo extends JpaRepository<VwDetailProduct, Long>
{
    List<VwDetailProduct> findAll();

    List<VwDetailProduct> findByProductId(Long productId);

    List<VwDetailProduct> findByCategoryId(Long categoryId);

    List<VwDetailProduct> findByUsername(String username);
}
