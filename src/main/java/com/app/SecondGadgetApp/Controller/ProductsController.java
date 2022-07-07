package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.ProductsDTO;
import com.app.SecondGadgetApp.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/product")
public class ProductsController
{
    @Autowired
    ProductsService productsService;

    @PostMapping("/add")
    public ResponseEntity<?> add(ProductsDTO productsDTO, @RequestParam("photo") MultipartFile file) throws IOException
    {
        productsDTO.setPhoto(file);

        return new ResponseEntity<>(productsService.add_product(productsDTO), HttpStatus.CREATED);
    }

    @PostMapping("/preview")
    public ResponseEntity<?> preview(ProductsDTO productsDTO, @RequestParam("photo") MultipartFile file) throws IOException
    {
        productsDTO.setPhoto(file);

        return new ResponseEntity<>(productsService.preview_product(productsDTO), HttpStatus.CREATED);
    }

    @GetMapping("/display-all")
    public ResponseEntity<?> display_all()
    {
        return new ResponseEntity<>(productsService.display_all(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/display/{id}")
    public ResponseEntity<?> display_product(@PathVariable("id") Long id)
    {
        if(productsService.display_product(id) != null)
        {
            return new ResponseEntity<>(productsService.display_product(id), HttpStatus.ACCEPTED);
        }
        else
        {
            return new ResponseEntity<>("product not found", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/display-status/{status}")
    public ResponseEntity<?> display_status(@PathVariable("status") String status)
    {
        if(productsService.display_status(status) != null)
        {
            return new ResponseEntity<>(productsService.display_status(status), HttpStatus.ACCEPTED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update_product(@PathVariable("id") Long id, ProductsDTO productsDTO, @RequestParam("photo") MultipartFile file) throws IOException
    {
        productsDTO.setPhoto(file);
        return new ResponseEntity<>(productsService.update_product(id, productsDTO), HttpStatus.ACCEPTED);
    }
}
