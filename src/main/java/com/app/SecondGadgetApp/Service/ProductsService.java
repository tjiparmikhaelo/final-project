package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.Products.ProductsDTO;
import com.app.SecondGadgetApp.Entity.Categories;
import com.app.SecondGadgetApp.Entity.ImageProducts;
import com.app.SecondGadgetApp.Entity.Products;
import com.app.SecondGadgetApp.Entity.Users;
import com.app.SecondGadgetApp.Repository.CategoriesRepo;
import com.app.SecondGadgetApp.Repository.ImageProductsRepo;
import com.app.SecondGadgetApp.Repository.ProductsRepo;
import com.app.SecondGadgetApp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
    UserRepo userRepo;
    @Autowired
    CategoriesRepo categoriesRepo;

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
        Users users = userRepo.findByUserId(productsDTO.getUserId());

        products.setProductName(productsDTO.getProductName());
        products.setCategories(categories);
        products.setPrice(productsDTO.getPrice());
        products.setSerialNumber(productsDTO.getSerialNumber());
        products.setDescription(productsDTO.getDescription());
        products.setProductStatus("available");
        products.setUsers(users);

        return productsRepo.save(products);
    }

    public List<Products> latest_product()
    {
        return productsRepo.findAll();
    }

    public List<Products> related_product(Long categoryId)
    {
        return productsRepo.findByCategoriesCategoryId(categoryId);
    }

    public List<Products> product_by_user(String username)
    {
        return productsRepo.findByUsersUsername(username);
    }

    public Products detail_product(Long productId)
    {
        return productsRepo.findByProductId(productId);
    }

    public Products edit_product(Long productId, ProductsDTO productsDTO)
    {
        Products products = productsRepo.findByProductId(productId);
        Categories categories = categoriesRepo.findByCategoryId(products.getCategories().getCategoryId());
        Users users = userRepo.findByUserId(products.getUsers().getUserId());

        products.setProductName(productsDTO.getProductName());
        products.setCategories(categories);
        products.setPrice(productsDTO.getPrice());
        products.setSerialNumber(productsDTO.getSerialNumber());
        products.setDescription(productsDTO.getDescription());
        products.setProductStatus("available");
        products.setUsers(users);

        return productsRepo.save(products);
    }

//    public void delete_product(Long productId)
//    {
//        ImageProducts imageProducts =
//    }
}
