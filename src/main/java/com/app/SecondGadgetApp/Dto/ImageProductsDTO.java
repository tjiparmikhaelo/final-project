package com.app.SecondGadgetApp.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ImageProductsDTO
{
    private Long imageProductId;
    private Long productId;
    private MultipartFile image;
}
