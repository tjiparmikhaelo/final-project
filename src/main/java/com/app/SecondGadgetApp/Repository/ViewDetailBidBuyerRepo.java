package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.View.ViewDetailBidBuyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ViewDetailBidBuyerRepo extends JpaRepository<ViewDetailBidBuyer, Long>
{
    List<ViewDetailBidBuyer> findAll();
    List<ViewDetailBidBuyer> findByBidId(Long bidId);
}
