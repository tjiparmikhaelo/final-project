package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriesRepo extends JpaRepository<Categories, Long>
{
    Categories findByCategoryId(Long categoryId);
    @Modifying
    @Query(value = "select * from categories c order by c.created_at", nativeQuery = true)
    List<Categories> showAll();
}
