package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.NotificationsDTO;
import com.app.SecondGadgetApp.Entity.Bids;
import com.app.SecondGadgetApp.Entity.Notifications;
import com.app.SecondGadgetApp.Repository.BidsRepo;
import com.app.SecondGadgetApp.Repository.NotificationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
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
        return notificationsRepo.findByBidsUsersUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Notifications> notification_buyer_mini(Long userId)
    {
        return notificationsRepo.miniBuyer(userId);
    }

    public void delete_buyer(Long userId)
    {
        notificationsRepo.findByBidsUsersUserId(userId);

        if(notificationsRepo.findByBidsUsersUserId(userId) != null)
        {
            notificationsRepo.deleteByBidsUsersUserId(userId);
        }
    }

    public void delete_all_buyer(Long userId)
    {
        notificationsRepo.findByBidsUsersUserId(userId);

        if (notificationsRepo.findByBidsUsersUserId(userId) != null)
        {
            notificationsRepo.deleteAllByBidsUsersUserId(userId);
        }
    }

    public List<Notifications> notification_seller_all(Long userId)
    {
        return notificationsRepo.findByBidsProductsUsersUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Notifications> notification_seller_mini(Long userId)
    {
        return notificationsRepo.miniSeller(userId);
    }

    public void delete_seller(Long userId)
    {
        notificationsRepo.findByBidsProductsUsersUserId(userId);

        if(notificationsRepo.findByBidsProductsUsersUserId(userId) != null)
        {
            notificationsRepo.deleteByBidsProductsUsersUserId(userId);
        }
    }

    public void delete_all_seller(Long userId)
    {
        notificationsRepo.findByBidsProductsUsersUserId(userId);

        if (notificationsRepo.findByBidsProductsUsersUserId(userId) != null)
        {
            notificationsRepo.deleteAllByBidsProductsUsersUserId(userId);
        }
    }
}
