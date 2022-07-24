package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.CategoriesDTO;
import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Entity.Categories;
import com.app.SecondGadgetApp.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoriesController
{
    @Autowired
    CategoriesService categoriesService;

    @PostMapping("/add")
    public ResponseEntity<?> add_category(CategoriesDTO categoriesDTO)
    {
        Categories categories = categoriesService.add_category(categoriesDTO);
        if (categories != null)
        {
            return new ResponseEntity<>(new ResponseDTO("201", "Kategori Telah Ditambahkan"), HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Kategori Gagal Ditambahkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> all_category()
    {
        List<Categories> display = categoriesService.show_all();
        if (display != null)
        {
            return new ResponseEntity<>(new ResponseDTO("201", "Kategori Berhasil Ditampilkan", categoriesService.show_all()), HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Kategori Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> category_by_id(@PathVariable("id") Long categoryId)
    {
        Categories categories = categoriesService.show_by_id(categoryId);
        if(categories != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Kategori Berhasil Ditampilkan", categories), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("400", "Kategori Tidak Ditemukan"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit_category(@PathVariable("id") Long categoryId, CategoriesDTO categoriesDTO)
    {
        Categories update = categoriesService.edit_category(categoryId, categoriesDTO);
        if (update != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Kategori Berhasil Diperbarui", update), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("400", "Kategori Gagal Diperbarui"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete_category(@PathVariable("id") Long categoryId)
    {
        categoriesService.delete_category(categoryId);
        return new ResponseEntity<>(new ResponseDTO("200", "Kategori Berhasil Dihapus"), HttpStatus.OK);
    }
}
