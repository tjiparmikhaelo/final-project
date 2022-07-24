package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.Product.ProductDTOEdit;
import com.app.SecondGadgetApp.Dto.Product.ProductsDTO;
import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Entity.Products;
import com.app.SecondGadgetApp.Repository.ImageProductsRepo;
import com.app.SecondGadgetApp.Repository.ProductsRepo;
import com.app.SecondGadgetApp.Service.ProductsService;
import com.app.SecondGadgetApp.Status.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
@Transactional
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
        return new ResponseEntity<>(new ResponseDTO("201", "Produk Berhasil Ditambahkan", products), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> productsWithFilter(
            @RequestParam(value = "productName") String productName,
            @RequestParam(value = "categoryId") Long categoryId,
            @RequestParam(value = "idCity")Long idCity
            )
    {
        List<Products> products = productsService.filter(productName, categoryId, idCity);
        if(products != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", products), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("400", "Produk Gagal Ditambahkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filter-by")
    public ResponseEntity<?> filter_by_keyword(@RequestParam(value = "productName") String productName)
    {
        List<Products> products =  productsService.filter_by_keyword(productName);
        if(products != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", products), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("400", "Produk Gagal Ditambahkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/latest")
    public ResponseEntity<?> latest_product()
    {
        List<Products> products = productsService.latest_product();
        if(products != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", products), HttpStatus.OK);

        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("400", "Produk Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/related/{id}")
    public ResponseEntity<?> related_product(@PathVariable("id") Long categoryId)
    {
        List<Products> products = productsService.related_product(categoryId);
        if(products != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", products), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("400", "Produk Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> product_by_user(@PathVariable("username") String username)
    {
        List<Products> products = productsService.product_by_user(username);
        if(products != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", products), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("400", "Produk Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail_product(@PathVariable("id") Long productId)
    {
        Products products = productsService.detail_product(productId);
        if(products != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Ditampilkan", products), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("400", "Produk Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/status/{id}")
    public ResponseEntity<?> edit_status_product(@PathVariable("id") Long productId, @RequestBody ProductsDTO productsDTO)
    {
        productsService.edit_product_status(productId, productsDTO);
        return new ResponseEntity<>(new ResponseDTO("200", "Status Produk Telah Diupdate"), HttpStatus.OK);
    }

    @PutMapping(value = "/edit/{id}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultStatus> updateProduct(ProductDTOEdit productDto, @PathVariable("id") Long productId)
    {
        return new ResponseEntity<>(productsService.edit_product(productDto, productId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> delete_product(@PathVariable("productId") Long productId)
    {
        productsService.delete_product(productId);
        return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Dihapus"), HttpStatus.OK);
    }
}

