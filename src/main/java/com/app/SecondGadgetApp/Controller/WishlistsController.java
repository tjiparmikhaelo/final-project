package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Dto.WishlistsDTO;
import com.app.SecondGadgetApp.Service.WishlistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistsController
{
    @Autowired
    WishlistsService wishlistsService;

    @PostMapping("/buyer/add")
    public ResponseEntity<?> add_wishlist(@RequestBody WishlistsDTO wishlistsDTO)
    {
        wishlistsService.add_wishlist(wishlistsDTO);
        return new ResponseEntity<>(new ResponseDTO("201", "Produk Favorit Berhasil Ditampilkan"), HttpStatus.CREATED);
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
}
