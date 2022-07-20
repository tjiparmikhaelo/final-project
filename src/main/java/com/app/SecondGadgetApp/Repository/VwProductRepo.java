package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.ViewProductFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VwProductRepo extends JpaRepository<ViewProductFilter, Long>
{
    List<ViewProductFilter> findAll();
    @Query(value = "select * from  vw_product_filter vw where vw.category_name like %:categoryName%", nativeQuery = true)
    List<ViewProductFilter> findByCategoryNameLike(@Param("categoryName") String categoryName);

    @Query(value = "select * from  vw_product_filter vw where vw.product_name like %:productName%", nativeQuery = true)
    List<ViewProductFilter> findByProductNameLike(@Param("productName") String productName);

    @Query(value = "select * from  vw_product_filter vw where price between :minPrice and :maxPrice", nativeQuery = true)
    List<ViewProductFilter> findByPrice(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);
}
