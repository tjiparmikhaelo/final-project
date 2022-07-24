package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface NotificationsRepo extends JpaRepository<Notifications, Long>
{
    List<Notifications> findByBidsUsersUserIdOrderByCreatedAtDesc(Long userId);
    List<Notifications> findByBidsUsersUserId(Long userId);
    List<Notifications> findByBidsProductsUsersUserId(Long userId);
    void deleteByBidsProductsProductId(Long productId);
    List<Notifications> deleteByBidsUsersUserId(Long userId);
    List<Notifications> deleteByBidsProductsUsersUserId(Long userId);
    List<Notifications> deleteAllByBidsUsersUserId(Long userId);
    List<Notifications> deleteAllByBidsProductsUsersUserId(Long userId);
    List<Notifications> findByBidsProductsUsersUserIdOrderByCreatedAtDesc(Long userId);
    @Modifying
    @Query(value = "select * from notifications n join bids b on n.bid_id = b.bid_id join users u on u.user_id = b.user_id where b.user_id = ?1 order by n.created_at desc limit 4", nativeQuery = true)
    List<Notifications> miniBuyer(Long userId);

    @Modifying
    @Query(value = "select * from notifications n join bids b on n.bid_id = b.bid_id join products p on b.product_id = p.product_id where p.user_id = ?1 order by n.created_at desc limit 4", nativeQuery = true)
    List<Notifications> miniSeller(Long userId);
}
