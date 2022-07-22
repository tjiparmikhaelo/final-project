package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.Carousels;
import com.app.SecondGadgetApp.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarouselsRepo extends JpaRepository <Carousels, Long> {
    Carousels findByCarouselId(Long carousel_id);
    List<Carousels> findAll();
}
