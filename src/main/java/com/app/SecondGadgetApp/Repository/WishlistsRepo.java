package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Entity.Wishlists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface WishlistsRepo extends JpaRepository<Wishlists, Long>
{
    @Query(value = "select * from wishlist w where w.user_id = ?1 order by created_at desc", nativeQuery = true)
    List<Wishlists> findByUsersUserId(Long userId);
    @Query(value = "select * from wishlist w where w.user_id = ?1 order by created_at desc limit 5", nativeQuery = true)
    List<Wishlists> miniWishlist(Long userId);
    Wishlists findByUsersUserIdAndProductsProductId(Long userId, Long productId);
    Wishlists findByWishlistId(Long wishlistId);
    List<Wishlists> deleteAllByUsersUserId(Long userId);
}
