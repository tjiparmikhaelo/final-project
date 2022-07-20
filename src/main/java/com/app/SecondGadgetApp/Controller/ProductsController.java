package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.ProductsDTO;
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

import java.math.BigDecimal;
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
            @RequestParam(value = "photo1", required = true) List<MultipartFile> photo1,
            @RequestParam(value = "photo2", required = false) List<MultipartFile> photo2,
            @RequestParam(value = "photo3", required = false) List<MultipartFile> photo3,
            @RequestParam(value = "photo4", required = false) List<MultipartFile> photo4
            )
    {
        Products products = productsService.add_product(productsDTO);
        productsService.imageProduct(photo1, products);
        productsService.imageProduct(photo2, products);
        productsService.imageProduct(photo3, products);
        productsService.imageProduct(photo4, products);
        return new ResponseEntity<>(new ResponseDTO("201", "Produk Berhasil Ditambahkan"), HttpStatus.CREATED);
    }
    @GetMapping("/withfilter")
    public ResponseEntity<?> productsWithFilter(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "categoryName", required = false) String categoryName,
            @RequestParam(value = "minPrice", required = false)BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false)BigDecimal maxPrice
            )
    {
        if(productName != null)
        {
            return new ResponseEntity<>(productsService.product_filter_product_name(productName), HttpStatus.OK);
        }
        else if(categoryName != null)
        {
            return new ResponseEntity<>(productsService.product_filter_category_name(categoryName), HttpStatus.OK);
        }
        else if (minPrice != null && maxPrice != null)
        {
            return new ResponseEntity<>(productsService.product_filter_price(minPrice, maxPrice), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Produk Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/latest")
    public ResponseEntity<?> latest_product()
    {
        if(productsService.latest_product() != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", productsService.latest_product()), HttpStatus.OK);

        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Produk Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/related/{id}")
    public ResponseEntity<?> related_product(@PathVariable("id") Long categoryId)
    {
        if(productsService.related_product(categoryId) != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", productsService.related_product(categoryId)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Produk Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> product_by_user(@PathVariable("username") String username)
    {
        if(productsService.product_by_user(username) != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", productsService.product_by_user(username)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Produk Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail_product(@PathVariable("id") Long productId)
    {
        if(productsService.detail_product(productId) != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", productsService.detail_product(productId)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Produk Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit_product(@PathVariable("id") Long productId, @RequestBody ProductsDTO productsDTO)
    {
        productsService.edit_product(productId, productsDTO);
        return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Diupdate"), HttpStatus.OK);
    }

    @PutMapping("/edit/status/{id}")
    public ResponseEntity<?> edit_status_product(@PathVariable("id") Long productId, @RequestBody ProductsDTO productsDTO)
    {
        productsService.edit_product_status(productId, productsDTO);
        return new ResponseEntity<>(new ResponseDTO("200", "Status Produk Telah Diupdate"), HttpStatus.OK);
    }
}

