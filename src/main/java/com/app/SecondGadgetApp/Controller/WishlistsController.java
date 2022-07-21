package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Dto.WishlistsDTO;
import com.app.SecondGadgetApp.Service.WishlistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        if (wishlistsService.check_wishlist(userId, productId) != null)
        {
            return new ResponseEntity<>(new ResponseDTO("200", "Produk Berhasil Dicek", wishlistsService.check_wishlist(userId, productId)), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO("404", "Produk Favorit Ditdak Ditemukan"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?> wishlist_all(@PathVariable("id") Long userId)
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Produk Favorit Berhasil Ditampilkan", wishlistsService.wishlist_all(userId)), HttpStatus.OK);
    }

    @GetMapping("/mini/{id}")
    public ResponseEntity<?> wishlist_mini(@PathVariable("id") Long userId)
    {
        return new ResponseEntity<>(new ResponseDTO("200", "Produk Favorit Berhasil Ditampilkan", wishlistsService.wishlist_mini(userId)), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> wishlist_delete(@PathVariable("id") Long wishlistId)
    {
        wishlistsService.delete_wishlist(wishlistId);
        return new ResponseEntity<>(new ResponseDTO("200", "Produk Favorit Berhasil Dihapus"), HttpStatus.OK);
    }

    @DeleteMapping("/delete-all/{id}")
    public ResponseEntity<?> wishlist_delet_all(@PathVariable("id") Long userId)
    {
        wishlistsService.delete_all_wishlist_by_userId(userId);
        return new ResponseEntity<>(new ResponseDTO("200", "Semua Produk Favorit Berhasil Dihapus"), HttpStatus.OK);
    }
}
