package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.BidsDTO;
import com.app.SecondGadgetApp.Entity.Bids;
import com.app.SecondGadgetApp.Repository.BidsRepo;
import com.app.SecondGadgetApp.Repository.ViewDetailBidBuyerRepo;
import com.app.SecondGadgetApp.Repository.ViewDetailBidSellerRepo;
import com.app.SecondGadgetApp.View.ViewDetailBidBuyer;
import com.app.SecondGadgetApp.View.ViewDetailBidSeller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidsService
{
    @Autowired
    BidsRepo bidsRepo;

    @Autowired
    ProductsService productsService;

    @Autowired
    ViewDetailBidBuyerRepo viewDetailBidBuyerRepo;

    @Autowired
    ViewDetailBidSellerRepo viewDetailBidSellerRepo;

    public Bids add_bid(BidsDTO bidsDTO)
    {
        Bids bids = new Bids();

        bids.setBuyerId(bidsDTO.getBuyerId());
        bids.setProductId(bidsDTO.getProductId());
        bids.setBidPrice(bidsDTO.getBidPrice());
        bids.setBidStatus("pending");
        return bidsRepo.save(bids);
    }

    public List<Bids> display_all()
    {
        return bidsRepo.findAll();
    }

    public Bids display_bid(Long bidId)
    {
        return bidsRepo.findByBidId(bidId);
    }

    public Bids update_bid(Long bidId, BidsDTO bidsDTO)
    {
        Bids bids = bidsRepo.findByBidId(bidId);
        try
        {
            bids.setBuyerId(bidsDTO.getBuyerId());
            bids.setProductId(bidsDTO.getProductId());
            bids.setBidPrice(bidsDTO.getBidPrice());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bidsRepo.save(bids);
    }
    //method to update the status, no endpoint
    public void update_status(String status, Long bidId)
    {
        Bids bids = bidsRepo.findByBidId(bidId);
        bids.setBidStatus(status);
        bidsRepo.save(bids);
    }

    public void accept_bid(Long bidId)
    {
        productsService.update_status("bidded", bidId);
        update_status("processed", bidId);
    }

    public void transaction_accepted (Long bidId)
    {
        productsService.update_status("sold", bidId);
        update_status("accepted", bidId);
    }

    public void transaction_declined (Long bidId)
    {
        update_status("declined", bidId);
    }

    public List<ViewDetailBidBuyer> display_bid_buyer(Long bidId)
    {
        return viewDetailBidBuyerRepo.findByBidId(bidId);
    }

    public List<ViewDetailBidSeller> display_all_buyer(Long sellerId)
    {
        return viewDetailBidSellerRepo.findBySellerId(sellerId);
    }
}