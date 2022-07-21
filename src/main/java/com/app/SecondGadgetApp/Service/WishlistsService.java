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

    public List<Wishlists> check_wishlist(Long userId, Long productId)
    {
        return wishlistsRepo.findByUsersUserIdAndProductsProductId(userId, productId);
    }

    public List<Wishlists> wishlist_all(Long userId)
    {
        return wishlistsRepo.findByUsersUserId(userId);
    }

    public List<Wishlists> wishlist_mini(Long userId)
    {
        return wishlistsRepo.miniWishlist(userId);
    }

    public void delete_wishlist(Long wishlistId)
    {
        wishlistsRepo.findByWishlistId(wishlistId);

        if (wishlistsRepo.findByWishlistId(wishlistId) != null)
        {
            wishlistsRepo.deleteById(wishlistId);
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