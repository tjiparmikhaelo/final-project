package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.ServicesImpl.CityServicesImpl;
import com.app.SecondGadgetApp.Status.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityServicesImpl cityServicesImpl;

    @GetMapping("/display-all")
    public ResponseEntity<ResultStatus> getAllCity()
    {
        return new ResponseEntity<>(cityServicesImpl.getAllCity(), HttpStatus.OK);
    }

    @GetMapping("/display-by-id/{city_id}")
    public ResponseEntity<ResultStatus> getbyCityId(@PathVariable("city_id") Long cityId)
    {
        return new ResponseEntity<>(cityServicesImpl.getCityById(cityId), HttpStatus.ACCEPTED);
    }
}