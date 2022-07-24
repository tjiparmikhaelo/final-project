package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.Product.ProductDTOEdit;
import com.app.SecondGadgetApp.Dto.Product.ProductsDTO;
import com.app.SecondGadgetApp.Entity.*;
import com.app.SecondGadgetApp.Repository.*;
import com.app.SecondGadgetApp.Status.ResultStatus;
import com.app.SecondGadgetApp.Status.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class ProductsService
{
    @Autowired
    ProductsRepo productsRepo;
    @Autowired
    ImageProductsRepo imageProductsRepo;
    @Autowired
    CloudinaryStorageServices cloudinaryStorageServices;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    CategoriesRepo categoriesRepo;
    @Autowired
    BidsRepo bidsRepo;
    @Autowired
    WishlistsRepo wishlistsRepo;
    @Autowired
    NotificationsRepo notificationsRepo;

    public String imageProduct(List<MultipartFile> image, Products products)
    {
        ImageProducts imageProducts = new ImageProducts();
        for (int i = 0; i < image.size(); i++)
        {
            Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageServices.upload(image.get(i)).getData();
            imageProducts.setImageUrl(uploadImage.get("url").toString());
            imageProducts.setProducts(products);
            imageProductsRepo.save(imageProducts);
        }
        return imageProducts.getImageUrl();
    }

    public Products add_product(ProductsDTO productsDTO)
    {
        Products products = new Products();
        Categories categories = categoriesRepo.findByCategoryId(productsDTO.getCategoryId());
        Users users = usersRepo.findByUserId(productsDTO.getUserId());

        products.setProductName(productsDTO.getProductName());
        products.setCategories(categories);
        products.setPrice(productsDTO.getPrice());
        products.setSerialNumber(productsDTO.getSerialNumber());
        products.setDescription(productsDTO.getDescription());
        products.setProductStatus("available");
        products.setUsers(users);

        return productsRepo.save(products);
    }

    public List<Products> filter(String productName, Long categoryId, Long cityId)
    {
        return productsRepo.filter(productName, categoryId, cityId);
    }

    public List<Products> filter_by_keyword(String productName)
    {
        return productsRepo.findByProductName(productName);
    }

    public List<Products> latest_product()
    {
        return productsRepo.ProductDesc();
    }

    public List<Products> related_product(Long categoryId)
    {
        return productsRepo.ProductByCategory(categoryId);
    }

    public List<Products> product_by_user(String username)
    {
        return productsRepo.ProductByUsername(username);
    }

    public Products detail_product(Long productId)
    {
        return productsRepo.findByProductId(productId);
    }

    public ResultStatus edit_product(ProductDTOEdit productsDTO, Long productId)
    {
        Products products = productsRepo.findByProductId(productId);
        Categories categories = categoriesRepo.findByCategoryId(productsDTO.getCategoryId());
        Users users = usersRepo.findByUserId(productsDTO.getUserId());

        if (productsDTO.getProductPhoto() == null)
        {
            products.setProductName(productsDTO.getProductName());
            products.setCategories(categories);
            products.setPrice(productsDTO.getPrice());
            products.setSerialNumber(productsDTO.getSerialNumber());
            products.setDescription(productsDTO.getDescription());
            products.setProductStatus(productsDTO.getProductStatus());
            products.setUsers(users);
        }
        else
        {
            products.setProductName(productsDTO.getProductName());
            products.setCategories(categories);
            products.setPrice(productsDTO.getPrice());
            products.setSerialNumber(productsDTO.getSerialNumber());
            products.setDescription(productsDTO.getDescription());
            products.setProductStatus(productsDTO.getProductStatus());
            products.setUsers(users);

            Iterable<ImageProducts> imageProducts = imageProductsRepo.findByProducts(products);
            imageProductsRepo.deleteAllInBatch(imageProducts);

            for (int i=0; i<productsDTO.getProductPhoto().size();i++)
            {
                Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageServices.upload(productsDTO.getProductPhoto().get(i)).getData();
                ImageProducts imageProduct = new ImageProducts();
                imageProduct.setProducts(products);
                imageProduct.setImageUrl(uploadImage.get("url").toString());
                imageProductsRepo.save(imageProduct);
            }
        }
        return new SuccessDataResult("Produk Berhasil Diperbarui");
    }

    public Products edit_product_status(Long productId, ProductsDTO productsDTO)
    {
        Products products = productsRepo.findByProductId(productId);

        products.setProductStatus(productsDTO.getProductStatus());

        return productsRepo.save(products);
    }

    public void delete_product(Long productId)
    {
        Products products = productsRepo.findByProductId(productId);

        if(products != null)
        {
//            bidsRepo.deleteByProductsProductId(productId);
//            wishlistsRepo.deleteByProductsProductId(productId);
//            notificationsRepo.deleteByBidsProductsProductId(productId);
            productsRepo.deleteByProductId(productId);
        }
    }
}