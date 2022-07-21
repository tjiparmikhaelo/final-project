package com.app.SecondGadgetApp.Repository;

import com.app.SecondGadgetApp.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository <City, Long>
{
    City findByIdCity(Long idCity);
}
