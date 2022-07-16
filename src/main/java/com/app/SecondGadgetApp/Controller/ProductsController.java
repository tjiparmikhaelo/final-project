package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.Products.ProductsDTO;
import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Entity.Products;
import com.app.SecondGadgetApp.Repository.ImageProductsRepo;
import com.app.SecondGadgetApp.Repository.ProductsRepo;
import com.app.SecondGadgetApp.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductsController {
    @Autowired
    ProductsService productsService;
    @Autowired
    ProductsRepo productsRepo;
    @Autowired
    ImageProductsRepo imageProductsRepo;

    @PostMapping("/add")
    public ResponseEntity<?> add_product(
            ProductsDTO productsDTO,
            @RequestParam(value = "photo1") List<MultipartFile> photo1,
            @RequestParam(value = "photo2") List<MultipartFile> photo2,
            @RequestParam(value = "photo3") List<MultipartFile> photo3,
            @RequestParam(value = "photo4") List<MultipartFile> photo4
            )
    {
        Products products = productsService.add_product(productsDTO);
        productsService.imageProduct(photo1, products);
        productsService.imageProduct(photo2, products);
        productsService.imageProduct(photo3, products);
        productsService.imageProduct(photo4, products);
        return new ResponseEntity<>(new ResponseDTO("201", "Produk Berhasil Ditambahkan"), HttpStatus.CREATED);
    }

    @GetMapping("/latest")
    public ResponseEntity<?> latest_product()
    {
        return new ResponseEntity<>(productsService.latest_product(), HttpStatus.OK);
    }

    @GetMapping("/related/{id}")
    public ResponseEntity<?> related_product(@PathVariable("id") Long categoryId)
    {
        return new ResponseEntity<>(productsService.related_product(categoryId), HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> product_by_user(@PathVariable("username") String username)
    {
        return new ResponseEntity<>(productsService.product_by_user(username), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail_product(@PathVariable("id") Long productId)
    {
        return new ResponseEntity<>(productsService.detail_product(productId), HttpStatus.OK);
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<?> edit_product(@PathVariable("id") Long productId, @RequestBody ProductsDTO productsDTO)
    {
        productsService.edit_product(productId, productsDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

