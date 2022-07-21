package com.app.SecondGadgetApp.Dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.util.Date;

@Setter
@Getter
public class CategoriesDTO
{
    private Long categoryId;
    private MultipartFile image;
    private String categoryName;
    private Date createdAt;
    private Date updatedAt;
}