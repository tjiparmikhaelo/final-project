package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationsRepo extends JpaRepository<Notifications, Long>
{
    List<Notifications> findByBidsUsersUserId(Long userId);
    List<Notifications> findByBidsProductsUsersUserId(Long userId);
}
