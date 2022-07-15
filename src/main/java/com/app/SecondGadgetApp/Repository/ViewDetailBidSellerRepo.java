package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.View.ViewDetailBidSeller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewDetailBidSellerRepo extends JpaRepository<ViewDetailBidSeller, Long>
{
    List<ViewDetailBidSeller> findAll();

    List<ViewDetailBidSeller> findBySellerId(Long sellerId);
}
