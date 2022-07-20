package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.NotificationsDTO;
import com.app.SecondGadgetApp.Entity.Bids;
import com.app.SecondGadgetApp.Entity.Notifications;
import com.app.SecondGadgetApp.Repository.BidsRepo;
import com.app.SecondGadgetApp.Repository.NotificationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsService
{
    @Autowired
    NotificationsRepo notificationsRepo;
    @Autowired
    BidsRepo bidsRepo;

    public Notifications add_notification(NotificationsDTO notificationsDTO)
    {
        Notifications notifications = new Notifications();
        Bids bids = bidsRepo.findByBidId(notificationsDTO.getBidId());

        notifications.setBids(bids);

        return notificationsRepo.save(notifications);
    }

    public List<Notifications> notification_buyer_all(Long userId)
    {
        return notificationsRepo.findByBidsUsersUserId(userId);
    }

    public List<Notifications> notification_seller_all(Long userId)
    {
        return notificationsRepo.findByBidsProductsUsersUserId(userId);
    }
}
