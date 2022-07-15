package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.ProductsDTO;
import com.app.SecondGadgetApp.Entity.Products;
import com.app.SecondGadgetApp.Repository.ProductsRepo;
import com.app.SecondGadgetApp.Repository.ViewDetailProductRepo;
import com.app.SecondGadgetApp.View.ViewDetailProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductsService
{
    @Autowired
    ProductsRepo productsRepo;

    @Autowired
    ViewDetailProductRepo viewDetailProductRepo;

    public Products add_product(ProductsDTO productsDTO) throws IOException
    {
        Products products = new Products();

        products.setSellerId(productsDTO.getSellerId());
        products.setCategoryId(productsDTO.getCategoryId());
        products.setProductName(productsDTO.getProductName());
        products.setSerialNumber(productsDTO.getSerialNumber());
        products.setPrice(productsDTO.getPrice());
        products.setDescription(productsDTO.getDescription());
        products.setProductStatus("available");
        products.setProductPhoto(productsDTO.getProductPhoto().getBytes());

        return productsRepo.save(products);
    }
    public Products preview_product(ProductsDTO productsDTO) throws IOException
    {
        Products products = new Products();

        products.setSellerId(productsDTO.getSellerId());
        products.setCategoryId(productsDTO.getCategoryId());
        products.setProductName(productsDTO.getProductName());
        products.setSerialNumber(productsDTO.getSerialNumber());
        products.setPrice(productsDTO.getPrice());
        products.setDescription(productsDTO.getDescription());
        products.setProductStatus("draft");
        products.setProductPhoto(productsDTO.getProductPhoto().getBytes());

        return productsRepo.save(products);
    }

    public List<Products> display_all()
    {
        return productsRepo.findAll();
    }

    public Products display_product(Long productId)
    {
        return productsRepo.findByProductId(productId);
    }

    public List<Products> display_status(String status)
    {
        return productsRepo.findByProductStatus(status);
    }

    public List<ViewDetailProduct> display_by_product(Long productId)
    {
        return viewDetailProductRepo.findByProductId(productId);
    }

    public List<ViewDetailProduct> display_by_category(Long categoryId)
    {
        return viewDetailProductRepo.findByCategoryId(categoryId);
    }

    public List<ViewDetailProduct> display_by_username(String username)
    {
        return viewDetailProductRepo.findByUsername(username);
    }

    public Products update_product(Long productId, ProductsDTO productsDTO)
    {
        Products products = productsRepo.findByProductId(productId);

        try
        {
            products.setSellerId(productsDTO.getSellerId());
            products.setCategoryId(productsDTO.getCategoryId());
            products.setProductName(productsDTO.getProductName());
            products.setSerialNumber(productsDTO.getSerialNumber());
            products.setPrice(productsDTO.getPrice());
            products.setDescription(productsDTO.getDescription());
            products.setProductPhoto(productsDTO.getProductPhoto().getBytes());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return productsRepo.save(products);
    }

    public void update_status(String status, Long productId)
    {
        Products products = productsRepo.findByProductId(productId);
        products.setProductStatus(status);
        productsRepo.save(products);
    }
}
