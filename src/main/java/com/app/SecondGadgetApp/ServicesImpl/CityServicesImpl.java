package com.app.SecondGadgetApp.ServicesImpl;

import com.app.SecondGadgetApp.Entity.City;
import com.app.SecondGadgetApp.Repository.CityRepo;
import com.app.SecondGadgetApp.Service.CityServices;
import com.app.SecondGadgetApp.Status.ErrorDataResult;
import com.app.SecondGadgetApp.Status.ResultStatus;
import com.app.SecondGadgetApp.Status.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServicesImpl implements CityServices {
    @Autowired
    CityRepo cityRepo;
    @Override
    public ResultStatus getAllCity() {
        List<City> cityList = cityRepo.findAll();
        return new SuccessDataResult(cityList,"Berhasil mendapat semua lis Kota!");
    }

    @Override
    public ResultStatus getCityById(Long idCity) {
        City city = cityRepo.getById(idCity);
        City cityVal = cityRepo.findByIdCity(city.getIdCity());
        if (cityVal != null) {
            return new ErrorDataResult("Id Kota tidak ditemukan");
        }
        return new SuccessDataResult(city,"Berhasil menampilkan Kota berdasarkan Id!");
    }
}
