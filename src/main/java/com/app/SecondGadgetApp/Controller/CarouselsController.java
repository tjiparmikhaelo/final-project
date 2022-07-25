package com.app.SecondGadgetApp.Controller;

import com.app.SecondGadgetApp.Dto.CarouselsDTO;
import com.app.SecondGadgetApp.Service.CarouselsService;
import com.app.SecondGadgetApp.Service.CloudinaryStorageServices;
import com.app.SecondGadgetApp.ServicesImpl.CarouselsServiceImpl;
import com.app.SecondGadgetApp.Status.ErrorDataResult;
import com.app.SecondGadgetApp.Status.ResultStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carousel")
public class CarouselsController {
    @Autowired
    private final CloudinaryStorageServices cloudinaryStorageServices;

    @Autowired
    private final CarouselsService carouselServices;

    @Autowired
    private final CarouselsServiceImpl carouselServicesImpl;

    @GetMapping("/all")
    public ResponseEntity<ResultStatus> getALlCarousel()
    {
        return new ResponseEntity<>(carouselServicesImpl.getAllCarousel(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add_carousel(CarouselsDTO carouselDto) {
        return new ResponseEntity<>(carouselServicesImpl.addCarousel(carouselDto), HttpStatus.CREATED);
    }

    @GetMapping("/details/{carousel_id}")
    public ResponseEntity<ResultStatus> getCarouselById(@PathVariable("carousel_id") Long carousel_id)
    {
        return new ResponseEntity<>(carouselServices.getCarouselById(carousel_id), HttpStatus.ACCEPTED);
    }

    @PutMapping("/edit/{carouselId}")
    public ResponseEntity<ResultStatus> update_carousel(@PathVariable("carouselId") long carouselId , CarouselsDTO carouselDto)
    {
        if (carouselServicesImpl.updateCarousel(carouselId, carouselDto) != null)
        {
            return new ResponseEntity<>(carouselServicesImpl.updateCarousel(carouselId, carouselDto), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ErrorDataResult("Kategori Gagal Diperbarui"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{carouselId}")
    public ResponseEntity<?> delete_carousel(@PathVariable ("carouselId") Long carousel_id)
    {
        return new ResponseEntity<>(carouselServices.deleteCarousel(carousel_id), HttpStatus.ACCEPTED);
    }
}