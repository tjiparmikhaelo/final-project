package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.CategoriesDTO;
import com.app.SecondGadgetApp.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/category")
public class CategoriesController
{
    @Autowired
    CategoriesService categoriesService;

    @PostMapping("/add")
    public ResponseEntity<?> add(CategoriesDTO categoriesDTO, @RequestParam("photo")MultipartFile file) throws IOException
    {
        categoriesDTO.setPhoto(file);
        return new ResponseEntity<>(categoriesService.add_category(categoriesDTO), HttpStatus.CREATED);
    }

    @GetMapping("/display-all")
    public ResponseEntity<?> display_all()
    {
        return new ResponseEntity<>(categoriesService.display_all(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/display/{id}")
    public ResponseEntity<?> display_category(@PathVariable("id") Long id)
    {
        if(categoriesService.display_category(id) != null)
        {
            return new ResponseEntity<>(categoriesService.display_category(id), HttpStatus.ACCEPTED);
        }
        else
        {
            return new ResponseEntity<>("category not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, CategoriesDTO categoriesDTO, @RequestParam("photo") MultipartFile file) throws IOException
    {
        categoriesDTO.setPhoto(file);
        return new ResponseEntity<>(categoriesService.update_category(id, categoriesDTO), HttpStatus.ACCEPTED);
    }
}
