package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.ProductsDTO;
import com.app.SecondGadgetApp.Dto.ResponseDTO;
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
    public ResponseEntity<?> add(ProductsDTO productsDTO, @RequestParam("productPhoto") MultipartFile file) throws IOException
    {
        productsDTO.setProductPhoto(file);
        productsService.add_product(productsDTO);
        return new ResponseEntity<>(new ResponseDTO("201", "Produk Berhasil Ditambahkan"), HttpStatus.CREATED);
    }

    @PostMapping("/preview")
    public ResponseEntity<?> preview(ProductsDTO productsDTO, @RequestParam("productPhoto") MultipartFile file) throws IOException
    {
        productsDTO.setProductPhoto(file);
        productsService.preview_product(productsDTO);
        return new ResponseEntity<>(new ResponseDTO("201", "Produk Berhasil Ditambahkan"), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> display_all()
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Disimpan", productsService.display_all()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/display/{id}")
    public ResponseEntity<?> display_product(@PathVariable("id") Long id)
    {
        if(productsService.display_product(id) != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200","success", productsService.display_product(id)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404","product not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detail/{productId}")
    public ResponseEntity<?> display_detail_product(@PathVariable("productId") Long productId)
    {
        if(productsService.display_by_product(productId) != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Detail Produk Berhasil Ditampilkan", productsService.display_by_product(productId)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404","Produk Tidak Ditemukan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("related/{categoryId}")
    public ResponseEntity<?> display_related_product(@PathVariable("categoryId") Long categoryId)
    {
        if(productsService.display_by_category(categoryId) != null)
        {
           return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", productsService.display_by_category(categoryId)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Produk Tidak Ditemukan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> display_by_username(@PathVariable("username") String username)
    {
        if(productsService.display_by_username(username) != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", productsService.display_by_username(username)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Produk Tidak Ditemukan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/display-status/{status}")
    public ResponseEntity<?> display_status(@PathVariable("status") String status)
    {
        if(productsService.display_status(status) != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "success", productsService.display_status(status)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "product not found"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update_product(@PathVariable("id") Long id, ProductsDTO productsDTO, @RequestParam("photo") MultipartFile file) throws IOException
    {
        productsDTO.setProductPhoto(file);
        return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Disimpan", productsService.update_product(id, productsDTO)), HttpStatus.OK);
    }
}
