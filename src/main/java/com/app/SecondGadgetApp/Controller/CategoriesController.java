package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.CategoriesDTO;
import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Entity.Categories;
import com.app.SecondGadgetApp.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoriesController
{
    @Autowired
    CategoriesService categoriesService;

    @PostMapping("/add")
    public ResponseEntity<?> add_category(CategoriesDTO categoriesDTO)
    {

        categoriesService.add_category(categoriesDTO);
        return new ResponseEntity<>(new ResponseDTO("201", "Kategori Telah Ditambahkan"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all_category()
    {
        if (categoriesService.show_all() != null)
        {
            return new ResponseEntity<>(new ResponseDTO("201", "Kategori Berhasil Ditampilkan", categoriesService.show_all()), HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Kategori Tidak Ditemukan"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit_category(@PathVariable("id") Long categoryId, @RequestBody CategoriesDTO categoriesDTO)
    {
        if (categoriesService.edit_category(categoryId, categoriesDTO) != null)
        {
            Categories update = categoriesService.edit_category(categoryId, categoriesDTO);
            return new ResponseEntity<>(new ResponseDTO("200", "Kategori Berhasil Diperbarui", update), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Kategori Gagal Diperbarui"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete_category(@PathVariable("id") Long categoryId)
    {
        categoriesService.delete_category(categoryId);
        return new ResponseEntity<>(new ResponseDTO("200", "Kategori Berhasil Dihapus"), HttpStatus.OK);
    }
}
