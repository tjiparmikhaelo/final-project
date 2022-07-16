package com.app.SecondGadgetApp.Dto.Products;

import com.app.SecondGadgetApp.Entity.Categories;
import com.app.SecondGadgetApp.Entity.ImageProducts;
import com.app.SecondGadgetApp.Entity.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
public class ProductsDetailDTO
{
    private Long productId;
    private Categories categories;
    private Users users;
    private String productName;
    private String serialNumber;
    private BigDecimal price;
    private String description;
    private String productStatus;
    private MultipartFile productPhoto;
    private Date createdAt;
    private Date updatedAt;
}
