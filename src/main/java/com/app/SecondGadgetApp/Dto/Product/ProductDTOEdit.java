package com.app.SecondGadgetApp.Dto.Product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ProductDTOEdit
{
    private Long productId;
    private Long categoryId;
    private Long userId;
    private String productName;
    private String serialNumber;
    private BigDecimal price;
    private String description;
    private String productStatus;
    private List<MultipartFile> productPhoto;
    private Date createdAt;
    private Date updatedAt;
}
