package com.app.SecondGadgetApp.Dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.util.Date;

@Setter
@Getter
public class WishlistsDTO
{
    private Long wishlistId;
    private Long userId;
    private Long productId;
    private Date createdAt;
    private Date updatedAt;
}
