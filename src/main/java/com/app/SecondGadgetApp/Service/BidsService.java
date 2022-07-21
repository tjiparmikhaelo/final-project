package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.BidsDTO;
import com.app.SecondGadgetApp.Entity.Bids;
import com.app.SecondGadgetApp.Entity.Products;
import com.app.SecondGadgetApp.Entity.Users;
import com.app.SecondGadgetApp.Repository.BidsRepo;
import com.app.SecondGadgetApp.Repository.ProductsRepo;
import com.app.SecondGadgetApp.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BidsService
{
    @Autowired
    BidsRepo bidsRepo;
    @Autowired
    UsersRepo usersRepo;
    @Autowired
    ProductsRepo productsRepo;

    public Bids add_bid(BidsDTO bidsDTO)
    {
        Bids bids = new Bids();
        Users users = usersRepo.findByUserId(bidsDTO.getUserId());
        Products products = productsRepo.findByProductId(bidsDTO.getProductId());

        bids.setUsers(users);
        bids.setProducts(products);
        bids.setBidPrice(bidsDTO.getBidPrice());
        bids.setBidStatus("pending");

        return bidsRepo.save(bids);
    }

    public List<Bids> show_all_bid_buyer(Long userId)
    {
        return bidsRepo.findByProductsUsersUserId(userId);
    }

    public Bids edit_bid_buyer(Long bidId, BidsDTO bidsDTO)
    {
        Bids bids = bidsRepo.findByBidId(bidId);

        bids.setBidStatus(bidsDTO.getBidStatus());
        bids.setBidPrice(bidsDTO.getBidPrice());

        return bidsRepo.save(bids);
    }

    public void delete_bid_buyer(Long bidId)
    {
        Bids bids = bidsRepo.findByBidId(bidId);

        if(bids != null)
        {
            bidsRepo.deleteById(bidId);
        }
    }

    public List<Bids> show_all_bid_seller(Long userId)
    {
        return bidsRepo.findByUsersUserId(userId);
    }

    public Bids edit_bid_seller(Long bidId, BidsDTO bidsDTO)
    {
        Bids bids = bidsRepo.findByBidId(bidId);

        bids.setBidStatus(bidsDTO.getBidStatus());
        bids.setBidPrice(bidsDTO.getBidPrice());

        return bidsRepo.save(bids);
    }

    public Bids show_detail_bid_buyer_seller(Long bidId)
    {
        return bidsRepo.findByBidId(bidId);
    }
}