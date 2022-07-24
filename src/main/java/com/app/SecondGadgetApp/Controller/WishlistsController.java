package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Dto.WishlistsDTO;
import com.app.SecondGadgetApp.Entity.Wishlists;
import com.app.SecondGadgetApp.Service.WishlistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
@Transactional
public class WishlistsController
{
    @Autowired
    WishlistsService wishlistsService;

    @PostMapping("/add")
    public ResponseEntity<?> add_wishlist(@RequestBody WishlistsDTO wishlistsDTO)
    {
        wishlistsService.add_wishlist(wishlistsDTO);
        return new ResponseEntity<>(new ResponseDTO("201", "Produk Berhasil Ditambahkan ke Favorit"), HttpStatus.CREATED);
    }

    @GetMapping("/check/{userId}/{productId}")
    public ResponseEntity<?> check_wishlist(
            @PathVariable("userId") Long userId,
            @PathVariable("productId") Long productId
            )
    {
        String wishlists = wishlistsService.check_wishlist(userId, productId);
        return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Dicek", wishlists), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?> wishlist_all(@PathVariable("id") Long userId)
    {
        ResponseDTO wishlists = wishlistsService.wishlist_all(userId);
        if (wishlists != null)
        {
            return new ResponseEntity<>(wishlists, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mini/{id}")
    public ResponseEntity<?> wishlist_mini(@PathVariable("id") Long userId)
    {
        List<Wishlists> wishlists = wishlistsService.wishlist_mini(userId);
        if (wishlists != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Favorit Berhasil Ditampilkan", wishlists), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("400", "Produk Favorit Gagal Ditampilkan"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{productId}/{userId}")
    public ResponseEntity<?> wishlist_delete(@PathVariable("productId") Long productId, @PathVariable("userId") Long userId)
    {
        wishlistsService.delete_wishlist(productId, userId);
        return new ResponseEntity<>(new ResponseDTO("200", "Produk Favorit Berhasil Dihapus"), HttpStatus.OK);
    }

    @DeleteMapping("/delete-all/{id}")
    public ResponseEntity<?> wishlist_delete_all(@PathVariable("id") Long productId)
    {
        wishlistsService.delete_all_wishlist_by_productId(productId);
        return new ResponseEntity<>(new ResponseDTO("200", "Semua Produk Favorit Berhasil Dihapus"), HttpStatus.OK);
    }
}
