package com.app.SecondGadgetApp.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Setter
@Getter
public class CarouselsDTO {
    private Long carouselId;
    private String carouselName;
    private String link;
    private MultipartFile img;
    private Date createdAt;
    private Date updatedAt;
}
