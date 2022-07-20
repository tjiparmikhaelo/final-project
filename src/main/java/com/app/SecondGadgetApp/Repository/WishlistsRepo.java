package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Dto.ResponseDTO;
import com.app.SecondGadgetApp.Entity.Wishlists;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistsRepo extends JpaRepository<Wishlists, Long>
{
    List<Wishlists> findByUsersUserId(Long userId);
    List<Wishlists> findByUsersUserIdAndProductsProductId(Long userId, Long productId);
}
