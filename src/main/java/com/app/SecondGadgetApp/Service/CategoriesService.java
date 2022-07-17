package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.CategoriesDTO;
import com.app.SecondGadgetApp.Entity.Categories;
import com.app.SecondGadgetApp.Repository.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoriesService
{
    @Autowired
    CategoriesRepo categoriesRepo;

    @Autowired
    CloudinaryStorageServices cloudinaryStorageServices;

    public Categories add_category(CategoriesDTO categoriesDTO)
    {
        Categories categories = new Categories();
        categories.setCategoryName(categoriesDTO.getCategoryName());
        Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageServices.upload(categoriesDTO.getImage()).getData();
        categories.setImage(uploadImage.get("url").toString());

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
        Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageServices.upload(categoriesDTO.getImage()).getData();
        update.setImage(uploadImage.get("url").toString());

        return categoriesRepo.save(update);
    }

    public void delete_category(Long id)
    {
        categoriesRepo.deleteById(id);
    }
}
