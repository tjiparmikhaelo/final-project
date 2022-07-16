package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.CategoriesDTO;
import com.app.SecondGadgetApp.Entity.Categories;
import com.app.SecondGadgetApp.Repository.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService
{
    @Autowired
    CategoriesRepo categoriesRepo;

    public Categories add_category(CategoriesDTO categoriesDTO)
    {
        Categories categories = new Categories();
        categories.setCategoryName(categoriesDTO.getCategoryName());

        return categoriesRepo.save(categories);
    }

    public List<Categories> show_all()
    {
        return categoriesRepo.findAll();
    }

    public Categories edit_category(Long id, CategoriesDTO categoriesDTO)
    {
        Categories update = categoriesRepo.findByCategoryId(id);
        update.setCategoryName(categoriesDTO.getCategoryName());

        return categoriesRepo.save(update);
    }

    public void delete_category(Long id)
    {
        categoriesRepo.deleteById(id);
    }
}
