package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.Bids;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BidsRepo extends JpaRepository<Bids, Long>
{
    Bids findByBidId(Long bidId);
    List<Bids> findByUsersUserId(Long userId);
    Bids findByUsersUserIdAndProductsProductId(Long userId, Long productId);
    List<Bids> findByProductsUsersUserId(Long userId);
    Bids findByProductsProductId(Long productId);
    void deleteByProductsProductId(Long productId);
}
