package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Dto.WishlistsDTO;
import com.app.SecondGadgetApp.Entity.Products;
import com.app.SecondGadgetApp.Entity.Users;
import com.app.SecondGadgetApp.Entity.Wishlists;
import com.app.SecondGadgetApp.Repository.ProductsRepo;
import com.app.SecondGadgetApp.Repository.UsersRepo;
import com.app.SecondGadgetApp.Repository.WishlistsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NonUniqueResultException;
import java.util.List;

@Transactional
@Service
public class WishlistsService {
    @Autowired
    WishlistsRepo wishlistsRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    ProductsRepo productsRepo;

    public Wishlists add_wishlist(WishlistsDTO wishlistsDTO)
    {
        Wishlists wishlists = new Wishlists();
        Users users = usersRepo.findByUserId(wishlistsDTO.getUserId());
        Products products = productsRepo.findByProductId(wishlistsDTO.getProductId());

        wishlists.setUsers(users);
        wishlists.setProducts(products);

        return wishlistsRepo.save(wishlists);
    }

    public String check_wishlist(Long userId, Long productId)
    {
        Wishlists wishlists = wishlistsRepo.findByUsersUserIdAndProductsProductId(userId, productId);
        if(wishlists != null)
        {
            return "true";
        }
        else
        {
            return "false";
        }
    }

    public ResponseDTO wishlist_all(Long userId)
    {
        List<Wishlists> wishlists = wishlistsRepo.findByUsersUserId(userId);
        if(wishlists != null)
        {
            return new ResponseDTO("200", "Produk Favorit Berhasil Ditampilkan", wishlists);
        }
        else
        {
           return new ResponseDTO("400", "Produk Favorit Gagal Ditampilkan");
        }
    }

    public List<Wishlists> wishlist_mini(Long userId)
    {
        return wishlistsRepo.miniWishlist(userId);
    }

    public void delete_wishlist(Long productId, Long userId)
    {
        Wishlists wishlists = wishlistsRepo.findByProductsProductId(productId);
        List<Wishlists> wishlists1 = wishlistsRepo.findByUsersUserId(userId);

        if (wishlists != null)
        {
            wishlistsRepo.deleteByProductsProductId(productId, userId);
        }
    }

    public void delete_all_wishlist_by_userId(Long userId)
    {
        wishlistsRepo.findByUsersUserId(userId);

        if(wishlistsRepo.findByUsersUserId(userId) != null)
        {
            wishlistsRepo.deleteAllByUsersUserId(userId);
        }
    }
}