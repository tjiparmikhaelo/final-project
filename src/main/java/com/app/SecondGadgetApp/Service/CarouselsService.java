package com.app.SecondGadgetApp.Service;

import com.app.SecondGadgetApp.Dto.CarouselsDTO;
import com.app.SecondGadgetApp.Status.ResultStatus;
import org.springframework.stereotype.Component;

@Component
public interface CarouselsService {

    //    ResultStatus updateCarousel(Long carousel_id, CarouselDto carouselDto);
    ResultStatus deleteCarousel(Long carousel_id);
    ResultStatus addCarousel(CarouselsDTO carouselDto);
    ResultStatus getCarouselById(Long carousel_id);
}
