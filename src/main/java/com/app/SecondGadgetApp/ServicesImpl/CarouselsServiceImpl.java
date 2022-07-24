package com.app.SecondGadgetApp.ServicesImpl;

import com.app.SecondGadgetApp.Dto.CarouselsDTO;
import com.app.SecondGadgetApp.Entity.Carousels;
import com.app.SecondGadgetApp.Repository.CarouselsRepo;
import com.app.SecondGadgetApp.Service.CarouselsService;
import com.app.SecondGadgetApp.Service.CloudinaryStorageServices;
import com.app.SecondGadgetApp.Status.ErrorDataResult;
import com.app.SecondGadgetApp.Status.ResultStatus;
import com.app.SecondGadgetApp.Status.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CarouselsServiceImpl implements CarouselsService {

    @Autowired
    CarouselsRepo carouselRepo;

    @Autowired
    CloudinaryStorageServices cloudinaryStorageServices;

    public ResultStatus getAllCarousel() {
        List<Carousels> carousel =carouselRepo.findAll();
        return new SuccessDataResult<>(carousel,"Berhasil mendapat semua data user");
    }

    @Override
    public ResultStatus addCarousel(CarouselsDTO carouselDto){
        Carousels carousel = new Carousels();
        carousel.setCarouselName(carouselDto.getCarouselName());
        carousel.setLink(carouselDto.getLink());
        Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageServices.upload(carouselDto.getImg()).getData();
        carousel.setImg(uploadImage.get("url").toString());
        Carousels saved = carouselRepo.save(carousel);
        return new SuccessDataResult(saved,"Berhasil menambahkan Carousel");
    }

    @Override
    public ResultStatus getCarouselById(Long carousel_id) {
        Carousels carousel = carouselRepo.findByCarouselId(carousel_id);
        return new SuccessDataResult<>(carousel,"Berhasil mendapat data user berdasarkan Id");
    }


    public ResultStatus updateCarousel(Long carousel_id, CarouselsDTO carouselDto) {
        Carousels carouselsRepos = carouselRepo.findByCarouselId(carousel_id);
        if (carouselDto.getImg()==null){
            carouselsRepos.setCarouselName(carouselDto.getCarouselName());
            carouselsRepos.setLink(carouselDto.getLink());
//            Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageServices.upload(carouselDto.getImg()).getData();
//            carouselsRepos.setImg(uploadImage.get("url").toString());
            Carousels saved = carouselRepo.save(carouselsRepos);
            return new SuccessDataResult(saved,"Carousel Berhasil Diperbarui");

        }else {
            carouselsRepos.setCarouselName(carouselDto.getCarouselName());
            carouselsRepos.setLink(carouselDto.getLink());
            Map<?, ?> uploadImage = (Map<?, ?>) cloudinaryStorageServices.upload(carouselDto.getImg()).getData();
            carouselsRepos.setImg(uploadImage.get("url").toString());
            Carousels saved = carouselRepo.save(carouselsRepos);
            return new SuccessDataResult(saved,"Carousel Berhasil Diperbarui");
        }
    }

    @Override
    public ResultStatus deleteCarousel(Long carousel_id) {
        Carousels carousel = carouselRepo.findByCarouselId(carousel_id);
        if (carousel != null){
            carouselRepo.deleteById(carousel_id);
            return new SuccessDataResult("Berhasil Menghapus Carousel!");
        }else {
            return new ErrorDataResult( "Gagal Menghapus Carousel!");
        }
    }
}